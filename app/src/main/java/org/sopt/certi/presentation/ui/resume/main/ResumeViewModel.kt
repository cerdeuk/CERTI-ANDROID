package org.sopt.certi.presentation.ui.resume.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.sopt.certi.core.state.UiState
import org.sopt.certi.domain.model.ActivityData
import org.sopt.certi.domain.model.certification.CertificationData
import org.sopt.certi.domain.usecase.AcquisitionUseCase
import org.sopt.certi.domain.usecase.ActivityUseCase
import org.sopt.certi.domain.usecase.CareerUseCase
import org.sopt.certi.domain.usecase.user.GetInterestedJobListUseCase
import org.sopt.certi.presentation.ui.resume.main.sideEffect.ResumeSideEffect
import org.sopt.certi.presentation.ui.resume.main.state.ResumeUiState
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class ResumeViewModel @Inject constructor(
    private val acquisitionUseCase: AcquisitionUseCase,
    private val getInterestJobListUseCase: GetInterestedJobListUseCase,
    private val careerUseCase: CareerUseCase,
    private val activityUseCase: ActivityUseCase
) : ViewModel() {
    private val _jobCategoryLoadState = MutableStateFlow<UiState<List<String>>>(UiState.Loading)
    private val _acquiredCertificationListLoadState = MutableStateFlow<UiState<List<CertificationData>>>(UiState.Loading)
    private val _experienceListLoadState = MutableStateFlow<UiState<List<ActivityData>>>(UiState.Loading)
    private val _activityListLoadState = MutableStateFlow<UiState<List<ActivityData>>>(UiState.Loading)
    private val _selectedCertDetail = MutableStateFlow<UiState<CertificationData>>(UiState.Loading)

    val resumeUiState: StateFlow<ResumeUiState> =
        combine(
            _jobCategoryLoadState,
            _acquiredCertificationListLoadState,
            _experienceListLoadState,
            _activityListLoadState,
            _selectedCertDetail
        ) { jobCategory, certList, experience, activity, certDetail ->
            ResumeUiState(
                jobCategoryLoadState = jobCategory,
                acquiredCertificationListLoadState = certList,
                experienceListLoadState = experience,
                activityListLoadState = activity,
                selectedCertDetail = certDetail
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = ResumeUiState(
                jobCategoryLoadState = UiState.Loading,
                acquiredCertificationListLoadState = UiState.Loading,
                experienceListLoadState = UiState.Loading,
                activityListLoadState = UiState.Loading,
                selectedCertDetail = UiState.Loading
            )
        )

    private val _sideEffect = Channel<ResumeSideEffect>()
    val sideEffect = _sideEffect.receiveAsFlow()

    fun getResumeData() {
        getJobCategory()
        getAcquiredCertificationList()
        getExperienceList()
        getActivityList()
    }

    private fun getJobCategory() = viewModelScope.launch {
        _jobCategoryLoadState.value = UiState.Loading
        getInterestJobListUseCase.invoke().fold(
            onSuccess = {
                val jobCategory = it.jobList
                _jobCategoryLoadState.emit(UiState.Success(jobCategory))
            },
            onFailure = {
                _jobCategoryLoadState.emit(UiState.Failure(it.message.toString()))
            }
        )
    }

    private fun getAcquiredCertificationList() = viewModelScope.launch {
        _acquiredCertificationListLoadState.value = UiState.Loading
        acquisitionUseCase.invoke().fold(
            onSuccess = {
                val acquiredCertificationList = it
                _acquiredCertificationListLoadState.emit(UiState.Success(acquiredCertificationList))
            },
            onFailure = {
                _acquiredCertificationListLoadState.emit(UiState.Failure(it.message.toString()))
            }
        )
    }

    private fun getExperienceList() = viewModelScope.launch {
        _experienceListLoadState.value = UiState.Loading
        careerUseCase.invoke().fold(
            onSuccess = {
                val experienceList = it
                _experienceListLoadState.emit(UiState.Success(experienceList))
            },
            onFailure = {
                _experienceListLoadState.emit(UiState.Failure(it.message.toString()))
            }
        )
    }

    private fun getActivityList() = viewModelScope.launch {
        _activityListLoadState.value = UiState.Loading
        activityUseCase.invoke().fold(
            onSuccess = {
                val activityList = it
                _activityListLoadState.emit(UiState.Success(activityList))
            },
            onFailure = {
                _activityListLoadState.emit(UiState.Failure(it.message.toString()))
            }
        )
    }

    fun onCertificationClick(selectedCertificationId: Long) = viewModelScope.launch {
        val detail = getCertificationDetail(selectedCertificationId)
        _selectedCertDetail.value = UiState.Success(detail)
        _sideEffect.send(ResumeSideEffect.ShowCertificationDetailModal)
    }

    private fun getCertificationDetail(selectedCertificationId: Long): CertificationData {
        val certificationData = CertificationData(
            certificationId = 1,
            certificationName = "GTQ 1급 (그래픽기술자격)",
            createdAt = LocalDate.now(),
            description = "1급과 2급, 급수의 차이는 이 업무를 수행하는 툴 활용 능력의 범위와 숙련도 등의 고도화 차이다.",
            index = 2,
            cardFrontImageUrl = "https://sopt-certi-bucket.s3.ap-northeast-2.amazonaws.com/certi/color%3Dblue.png",
            cardBackImageUrl = "https://sopt-certi-bucket.s3.ap-northeast-2.amazonaws.com/certi/Property+1%3D3.png",
            tags = listOf("태그", "태그", "태그")
        )
        return certificationData
    }
}
