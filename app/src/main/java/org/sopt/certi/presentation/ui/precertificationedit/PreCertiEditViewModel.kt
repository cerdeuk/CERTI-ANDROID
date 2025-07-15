package org.sopt.certi.presentation.ui.precertificationedit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import org.sopt.certi.core.state.UiState
import org.sopt.certi.domain.model.CertificationData
import org.sopt.certi.domain.usecase.DummyUseCase
import org.sopt.certi.presentation.ui.precertificationedit.state.PreCertiEditUiState
import javax.inject.Inject

@HiltViewModel
class PreCertiEditViewModel @Inject constructor(
    private val dummyUseCase: DummyUseCase
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
        val preCertiEditList = {
            listOf<CertificationData>(
                CertificationData(
                    certificationId = 0,
                    certificationName = "시각디자인산업기사",
                    averagePeriod = "3개월",
                    nearestTestDate = "2025.05.27",
                    agencyName = "한국산업인력공단",
                    iconIndex = 0
                ),
                CertificationData(
                    certificationId = 1,
                    certificationName = "시각디자인산업기사",
                    averagePeriod = "3개월",
                    nearestTestDate = "2025.05.27",
                    agencyName = "한국산업인력공단",
                    iconIndex = 1
                ),
                CertificationData(
                    certificationId = 2,
                    certificationName = "시각디자인산업기사",
                    averagePeriod = "3개월",
                    nearestTestDate = "2025.05.27",
                    agencyName = "한국산업인력공단",
                    iconIndex = 2
                )
            )
        }
        _preCertiEditListLoadState.value = UiState.Success(preCertiEditList())
    }

    fun onDeleteClick(id: Long) {
        _selectedDeleteItem.value = id
        _showDialog.value = true
    }

    fun onDialogConfirm() {
        val currentState = _preCertiEditListLoadState.value
        val deleteId = _selectedDeleteItem.value

        if (currentState is UiState.Success && deleteId != null) {
            val updatedList = currentState.data.filterNot { it.certificationId == deleteId }

            _preCertiEditListLoadState.value = UiState.Success(updatedList)
        }

        _showDialog.value = false
        _selectedDeleteItem.value = null
    }

    fun onDialogDismiss() {
        _showDialog.value = false
        _selectedDeleteItem.value = null
    }
}
