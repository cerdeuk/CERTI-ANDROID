package org.sopt.certi.presentation.ui.resume.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.sopt.certi.core.state.UiState
import org.sopt.certi.domain.model.ActivityData
import org.sopt.certi.domain.model.CertificationData
import org.sopt.certi.domain.usecase.DummyUseCase
import org.sopt.certi.presentation.ui.resume.main.sideEffect.ResumeSideEffect
import org.sopt.certi.presentation.ui.resume.main.state.ResumeUiState
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class ResumeViewModel @Inject constructor(
    private val dummyUseCase: DummyUseCase
) : ViewModel() {
    private val _jobCategoryLoadState = MutableStateFlow<UiState<List<String>>>(UiState.Loading)
    private val _acquiredCertificationListLoadState = MutableStateFlow<UiState<List<CertificationData>>>(UiState.Loading)
    private val _experienceListLoadState = MutableStateFlow<UiState<List<ActivityData>>>(UiState.Loading)
    private val _activityListLoadState = MutableStateFlow<UiState<List<ActivityData>>>(UiState.Loading)

    val resumeUiState: StateFlow<ResumeUiState> =
        combine(
            _jobCategoryLoadState,
            _acquiredCertificationListLoadState,
            _experienceListLoadState,
            _activityListLoadState
        ) { jobCategory, certList, experience, activity ->
            ResumeUiState(
                jobCategoryLoadState = jobCategory,
                acquiredCertificationListLoadState = certList,
                experienceListLoadState = experience,
                activityListLoadState = activity
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = ResumeUiState(
                jobCategoryLoadState = UiState.Init,
                acquiredCertificationListLoadState = UiState.Init,
                experienceListLoadState = UiState.Init,
                activityListLoadState = UiState.Init
            )
        )

    private val _sideEffect = MutableSharedFlow<ResumeSideEffect>()
    val sideEffect = _sideEffect

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

    private fun getAcquiredCertificationList() {
        val acquiredCertificationList = {
            listOf(
                CertificationData(
                    certificationId = 1,
                    certificationName = "GTQ 1급 (그래픽기술자격)",
                    createdAt = LocalDate.now(),
                    description = "• 1급과 2급, 급수의 차이는 이 업무를 수행하는 툴 활용 능력의 범위와 숙련도 등의 고도화 차이다.",
                    index = 2,
                    cardFrontImageUrl = "https://sopt-certi-bucket.s3.ap-northeast-2.amazonaws.com/certi/color%3Dblue.png",
                    cardBackImageUrl = "https://sopt-certi-bucket.s3.ap-northeast-2.amazonaws.com/certi/Property+1%3D3.png",
                    tags = listOf("태그", "태그", "태그")
                ),
                CertificationData(
                    certificationId = 2,
                    certificationName = "GTQ 1급 (그래픽기술자격)",
                    createdAt = LocalDate.now(),
                    description = "• 1급과 2급, 급수의 차이는 이 업무를 수행하는 툴 활용 능력의 범위와 숙련도 등의 고도화 차이다.",
                    index = 3,
                    cardFrontImageUrl = "https://sopt-certi-bucket.s3.ap-northeast-2.amazonaws.com/certi/color%3Dwhite.png",
                    cardBackImageUrl = "https://sopt-certi-bucket.s3.ap-northeast-2.amazonaws.com/certi/Property+1%3D3.png",
                    tags = listOf("태그", "태그", "태그")
                ),
                CertificationData(
                    certificationId = 3,
                    certificationName = "GTQ 1급 (그래픽기술자격)",
                    createdAt = LocalDate.now(),
                    description = "• 1급과 2급, 급수의 차이는 이 업무를 수행하는 툴 활용 능력의 범위와 숙련도 등의 고도화 차이다.",
                    index = 1,
                    cardFrontImageUrl = "https://sopt-certi-bucket.s3.ap-northeast-2.amazonaws.com/certi/color%3Dyellow.png",
                    cardBackImageUrl = "https://sopt-certi-bucket.s3.ap-northeast-2.amazonaws.com/certi/Property+1%3D3.png",
                    tags = listOf("태그", "태그", "태그")
                ),
                CertificationData(
                    certificationId = 4,
                    certificationName = "GTQ 1급 (그래픽기술자격)",
                    createdAt = LocalDate.now(),
                    description = "• 1급과 2급, 급수의 차이는 이 업무를 수행하는 툴 활용 능력의 범위와 숙련도 등의 고도화 차이다.",
                    index = 2,
                    cardFrontImageUrl = "https://sopt-certi-bucket.s3.ap-northeast-2.amazonaws.com/certi/color%3Dblue.png",
                    cardBackImageUrl = "https://sopt-certi-bucket.s3.ap-northeast-2.amazonaws.com/certi/Property+1%3D3.png",
                    tags = listOf("태그", "태그", "태그")
                )
            )
        }
        _acquiredCertificationListLoadState.value = UiState.Success(acquiredCertificationList().take(3))
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

    fun onCertificationClick(selectedCertificationId: Long) {
        viewModelScope.launch {
            val detail = getCertificationDetail(selectedCertificationId)
            _sideEffect.emit(ResumeSideEffect.ShowCertificationDetailModal(detail))
        }
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
            _sideEffect.emit(ResumeSideEffect.HideCertificationDetailModal)
        }
    }
}
