package org.sopt.certi.presentation.ui.myCert

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
import org.sopt.certi.domain.usecase.AcquisitionUseCase
import org.sopt.certi.presentation.ui.myCert.sideEffect.MyCertSideEffect
import org.sopt.certi.presentation.ui.myCert.state.MyCertUiState
import javax.inject.Inject

@HiltViewModel
class MyCertViewModel @Inject constructor(
    private val acquisitionUseCase: AcquisitionUseCase
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

    fun getMyCertList() = viewModelScope.launch {
        _myCertListLoadState.value = UiState.Loading
        acquisitionUseCase.invoke().fold(
            onSuccess = {
                val acquiredCertificationList = it
                _myCertListLoadState.emit(UiState.Success(acquiredCertificationList))
            },
            onFailure = {
                _myCertListLoadState.emit(UiState.Failure(it.message.toString()))
            }
        )
    }

    fun onDeleteClick(certificationId: Long) = viewModelScope.launch {
        _selectedCertificationId.value = certificationId
        _sideEffect.send(MyCertSideEffect.ShowDeleteDialog)
    }

    fun onConfirmDelete() = viewModelScope.launch {
        _selectedCertificationId.value = null
        getMyCertList()
    }
}
