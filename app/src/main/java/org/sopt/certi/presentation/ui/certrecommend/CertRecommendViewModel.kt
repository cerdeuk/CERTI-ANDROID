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
import org.sopt.certi.domain.model.certification.CertificationData
import org.sopt.certi.domain.usecase.DummyUseCase
import org.sopt.certi.domain.usecase.certification.GetRecommendCertListUseCase
import org.sopt.certi.domain.usecase.user.GetInterestedJobListUseCase
import org.sopt.certi.domain.usecase.user.ModifyInterestedJobListUseCase
import org.sopt.certi.presentation.ui.certrecommend.sideeffect.RecommendSideEffect
import org.sopt.certi.presentation.ui.certrecommend.state.RecommendUiState
import javax.inject.Inject

@HiltViewModel
class CertRecommendViewModel @Inject constructor(
    private val dummyUseCase: DummyUseCase,
    private val getInterestedJobListUseCase: GetInterestedJobListUseCase,
    private val getRecommendCertListUseCase: GetRecommendCertListUseCase,
    private val modifyInterestedJobListUseCase: ModifyInterestedJobListUseCase
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

                getRecommendCertList()
                _jobList.emit(UiState.Success(categoryList))
            },
            onFailure = {
                _jobList.emit(UiState.Failure(it.message.toString()))
            }
        )
    }

    private fun getRecommendCertList() = viewModelScope.launch {
        getRecommendCertListUseCase.invoke().fold(
            onSuccess = {
                _recommendCertList.emit(UiState.Success(it.certificationList))
            },
            onFailure = {
                _recommendCertList.emit(UiState.Failure(it.message.toString()))
            }
        )
    }

    fun editJob(jobNameList: List<String>) = viewModelScope.launch {
        modifyInterestedJobListUseCase.invoke(jobNameList).fold(
            onSuccess = {
                getJobList()
            },
            onFailure = {
                _jobList.emit(UiState.Failure(it.message.toString()))
            }
        )
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
