package org.sopt.certi.presentation.ui.resume.myCert

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
import org.sopt.certi.domain.model.CertificationData
import org.sopt.certi.domain.usecase.DummyUseCase
import org.sopt.certi.presentation.ui.resume.myCert.sideEffect.MyCertSideEffect
import org.sopt.certi.presentation.ui.resume.myCert.state.MyCertUiState
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class MyCertViewModel @Inject constructor(
    private val dummyUseCase: DummyUseCase
) : ViewModel() {
    private val _myCertListLoadState = MutableStateFlow<UiState<List<CertificationData>>>(UiState.Loading)
    private val _selectedCertificationId = MutableStateFlow<Long?>(null)

    val myCertUiState: StateFlow<MyCertUiState> =
        combine(
            _myCertListLoadState,
            _selectedCertificationId
        ) { listState, selectedId ->
            MyCertUiState(
                myCertListLoadState = listState,
                selectedCertificationId = selectedId
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = MyCertUiState(
                myCertListLoadState = UiState.Loading,
                selectedCertificationId = null
            )
        )

    private val _sideEffect = Channel<MyCertSideEffect>()
    val sideEffect = _sideEffect.receiveAsFlow()

    fun getMyCertList() {
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
        _myCertListLoadState.value = UiState.Success(acquiredCertificationList())
    }

    fun onDeleteClick(certificationId: Long) = viewModelScope.launch {
        _selectedCertificationId.value = certificationId
        _sideEffect.send(MyCertSideEffect.ShowDeleteDialog)
    }

    fun onDismissDeleteDialog() = viewModelScope.launch {
        _selectedCertificationId.value = null
        _sideEffect.send(MyCertSideEffect.HideDeleteDialog)
    }

    fun onConfirmDelete() = viewModelScope.launch {
        _selectedCertificationId.value = null
        _sideEffect.send(MyCertSideEffect.HideDeleteDialog)

        val acquiredCertificationList = {
            listOf(
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
        _myCertListLoadState.value = UiState.Success(acquiredCertificationList())
    }
}
