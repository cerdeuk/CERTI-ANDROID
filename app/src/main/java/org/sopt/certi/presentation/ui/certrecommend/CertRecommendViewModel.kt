package org.sopt.certi.presentation.ui.certrecommend

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
import org.sopt.certi.domain.usecase.GetInterestedJobListUseCase
import org.sopt.certi.presentation.ui.certrecommend.sideeffect.RecommendSideEffect
import org.sopt.certi.presentation.ui.certrecommend.state.RecommendUiState
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class CertRecommendViewModel @Inject constructor(
    private val dummyUseCase: DummyUseCase,
    private val getInterestedJobListUseCase: GetInterestedJobListUseCase
) : ViewModel() {

    private val _jobList = MutableStateFlow<UiState<List<String>>>(UiState.Init)
    private val _recommendCertList = MutableStateFlow<UiState<List<CertificationData>>>(UiState.Init)

    val recommendUiState: StateFlow<RecommendUiState> =
        combine(
            _recommendCertList,
            _jobList
        ) { recommendCerList, jobList ->
            RecommendUiState(
                recommendCertListLoadState = recommendCerList,
                jobListLoadState = jobList
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = RecommendUiState(
                recommendCertListLoadState = UiState.Init,
                jobListLoadState = UiState.Init
            )
        )

    private val _sideEffect = Channel<RecommendSideEffect>()
    val sideEffect = _sideEffect.receiveAsFlow()

    fun getJobList() = viewModelScope.launch {
        getInterestedJobListUseCase.invoke().fold(
            onSuccess = {
                val categoryList = it.jobList

                getRecommendCertList(categoryList)
                _jobList.emit(UiState.Success(categoryList))
            },
            onFailure = {
                _jobList.emit(UiState.Failure(it.message.toString()))
            }
        )
    }

    private fun getRecommendCertList(dummyCertList: List<String>) = viewModelScope.launch {
        // TODO 추천 자격증 리스트 받아오는 로직

        val dummyRecommendList = mutableListOf<CertificationData>()
        for (i in 0L..11L) {
            dummyRecommendList.add(
                CertificationData(
                    certificationId = i,
                    certificationName = dummyCertList[0],
                    agencyName = "국가기술자격",
                    createdAt = LocalDate.now(),
                    cardFrontImageUrl = "https://sopt-certi-bucket.s3.ap-northeast-2.amazonaws.com/certi/color%3Dblue.png",
                    tags = listOf("태그", "태그", "태그")
                )
            )
        }

        _recommendCertList.emit(UiState.Success(dummyRecommendList))
    }

    fun editJob(jobNameList: List<String>) = viewModelScope.launch {
        // TODO 희망직무 수정 로직

        // 정상적으로 response 받았다는 가정하에
//        getJobList(jobNameList)
    }

    fun onLikeClick(certId: Long) {
        val currentState = _recommendCertList.value
        if (currentState is UiState.Success) {
            val updated = currentState.data.map {
                if (it.certificationId == certId) {
                    it.copy(isFavorite = !it.isFavorite)
                } else {
                    it
                }
            }
            _recommendCertList.value = UiState.Success(updated)
        }
    }

    fun showFilterBottomSheet() = viewModelScope.launch {
        _sideEffect.send(RecommendSideEffect.ShowFilterBottomSheer)
    }
}
