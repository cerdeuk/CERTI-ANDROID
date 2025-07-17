package org.sopt.certi.presentation.ui.precertificationedit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.sopt.certi.core.state.UiState
import org.sopt.certi.domain.model.certification.CertificationData
import org.sopt.certi.domain.usecase.PreCertEditUseCase
import org.sopt.certi.domain.usecase.PreCertUseCase
import org.sopt.certi.presentation.ui.precertificationedit.state.PreCertiEditUiState
import javax.inject.Inject

@HiltViewModel
class PreCertiEditViewModel @Inject constructor(
    private val preCertUseCase: PreCertUseCase,
    private val preCertEditUseCase: PreCertEditUseCase
) : ViewModel() {
    private val _preCertiEditListLoadState = MutableStateFlow<UiState<List<CertificationData>>>(UiState.Loading)
    private val _showDialog = MutableStateFlow(false)
    private val _selectedDeleteItem = MutableStateFlow<Long?>(null)

    val preCertiEditUiState: StateFlow<PreCertiEditUiState> =
        combine(_preCertiEditListLoadState, _showDialog, _selectedDeleteItem) { preCertiEditListLoadState, showDialog, selectedDeleteItem ->
            PreCertiEditUiState(
                preCertiListLoadState = preCertiEditListLoadState,
                showDialog = showDialog,
                selectedDeleteItem = selectedDeleteItem
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = PreCertiEditUiState(
                preCertiListLoadState = UiState.Loading,
                showDialog = false,
                selectedDeleteItem = 0
            )
        )

    fun getPreCertiEditList() {
        viewModelScope.launch {
            _preCertiEditListLoadState.value = UiState.Loading

            preCertUseCase()
                .onSuccess { result ->
                    if (result.isEmpty()) {
                        _preCertiEditListLoadState.value = UiState.Empty
                    } else {
                        _preCertiEditListLoadState.value = UiState.Success(result)
                    }
                }
                .onFailure {
                    _preCertiEditListLoadState.value = UiState.Failure(it.toString())
                }
        }
    }

    fun onDeleteClick(id: Long) {
        _selectedDeleteItem.value = id
        _showDialog.value = true
    }

    fun onDialogConfirm() {
        val deleteId = _selectedDeleteItem.value ?: return

        viewModelScope.launch {
            preCertEditUseCase(deleteId)
                .onSuccess {
                    val currentState = _preCertiEditListLoadState.value
                    if (currentState is UiState.Success) {
                        val updatedList = currentState.data.filterNot { it.certificationId == deleteId }
                        _preCertiEditListLoadState.value =
                            if (updatedList.isEmpty()) {
                                UiState.Empty
                            } else {
                                UiState.Success(updatedList)
                            }
                    }
                }
                .onFailure {
                    _preCertiEditListLoadState.value = UiState.Failure(it.toString())
                }

            _showDialog.value = false
            _selectedDeleteItem.value = null
        }
    }

    fun onDialogDismiss() {
        _showDialog.value = false
        _selectedDeleteItem.value = null
    }
}
