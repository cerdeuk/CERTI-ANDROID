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
import org.sopt.certi.presentation.ui.resume.main.sideEffect.ResumeSideEffect
import org.sopt.certi.presentation.ui.resume.main.state.ResumeUiState
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class ResumeViewModel @Inject constructor(
    private val acquisitionUseCase: AcquisitionUseCase
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

    private fun getJobCategory() {
        val jobCategory = {
            listOf("IT/인터넷", "IT/인터넷", "IT/인터넷")
        }
        _jobCategoryLoadState.value = UiState.Success(jobCategory())
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

    private fun getExperienceList() {
        val experienceList = {
            listOf(
                ActivityData(
                    startAt = "2021.11",
                    endAt = "2022.01",
                    organization = "서티그룹",
                    role = "패션디자이너 인턴",
                    description = "트렌드 리서치 및 소재 조사, 트렌드 리서치 및 소재 조사"
                ),
                ActivityData(
                    startAt = "2021.11",
                    endAt = "2022.01",
                    organization = "서티그룹",
                    role = "패션디자이너 인턴",
                    description = "트렌드 리서치 및 소재 조사"
                )
            )
        }
        _experienceListLoadState.value = UiState.Success(experienceList().take(4))
    }

    private fun getActivityList() {
        val activityList = {
            listOf(
                ActivityData(
                    startAt = "2021.11",
                    endAt = "2022.01",
                    organization = "sopt",
                    role = "동아리 36기 기획",
                    description = "서비스 기획 및 아이디어 도출"
                ),
                ActivityData(
                    startAt = "2021.11",
                    endAt = "2022.01",
                    organization = "sopt",
                    role = "동아리 36기 기획",
                    description = "서비스 기획 및 아이디어 도출"
                ),
                ActivityData(
                    startAt = "2021.11",
                    endAt = "2022.01",
                    organization = "sopt",
                    role = "동아리 36기 기획",
                    description = "서비스 기획 및 아이디어 도출"
                ),
                ActivityData(
                    startAt = "2021.11",
                    endAt = "2022.01",
                    organization = "sopt",
                    role = "동아리 36기 기획",
                    description = "서비스 기획 및 아이디어 도출"
                ),
                ActivityData(
                    startAt = "2021.11",
                    endAt = "2022.01",
                    organization = "sopt",
                    role = "동아리 36기 기획",
                    description = "서비스 기획 및 아이디어 도출"
                ),
                ActivityData(
                    startAt = "2021.11",
                    endAt = "2022.01",
                    organization = "sopt",
                    role = "동아리 36기 기획",
                    description = "서비스 기획 및 아이디어 도출"
                )
            )
        }
        _activityListLoadState.value = UiState.Success(activityList().take(4))
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

    fun onCertificationDetailDismiss() {
        viewModelScope.launch {
            _sideEffect.send(ResumeSideEffect.HideCertificationDetailModal)
        }
    }
}
