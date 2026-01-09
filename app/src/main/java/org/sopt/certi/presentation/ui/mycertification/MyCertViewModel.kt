package org.sopt.certi.presentation.ui.mycertification

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.sopt.certi.core.state.UiState
import org.sopt.certi.presentation.type.MyCertType
import org.sopt.certi.presentation.ui.mycertification.state.MyCertUiState
import javax.inject.Inject

@HiltViewModel
class MyCertViewModel @Inject constructor() : ViewModel() {
    private val _myCertUiState = MutableStateFlow(
        MyCertUiState(
            isEditMode = false,
            selectedTab = MyCertType.PLANNED,
            myCertListLoadState = UiState.Init,
            editTargetCertification = null,
            deleteTargetId = null
        )
    )
    val myCertUiState = _myCertUiState.asStateFlow()

    init {
        getPlannedCertificationList()
    }

    fun updateSelectedTab(tabType: MyCertType) {
        if (_myCertUiState.value.isEditMode) return
        if (_myCertUiState.value.selectedTab == tabType) return

        _myCertUiState.update {
            it.copy(
                selectedTab = tabType,
                myCertListLoadState = UiState.Loading
            )
        }

        when (tabType) {
            MyCertType.PLANNED -> getPlannedCertificationList()
            MyCertType.ACQUIRED -> getAcquiredCertificationList()
            MyCertType.FAVORITE -> getFavoriteCertificationList()
        }
    }

    private fun getPlannedCertificationList() = viewModelScope.launch {
        _myCertUiState.update { it.copy(myCertListLoadState = UiState.Success(dummyPlannedCertifications)) }
    }

    private fun getAcquiredCertificationList() = viewModelScope.launch {
        _myCertUiState.update { it.copy(myCertListLoadState = UiState.Success(dummyAcquiredCertification)) }
    }

    private fun getFavoriteCertificationList() = viewModelScope.launch {
        _myCertUiState.update { it.copy(myCertListLoadState = UiState.Success(dummyPlannedCertifications)) }
    }

    fun onFavoriteToggle(id: Long) {}

    fun onEditModeToggle() {
        val isEditMode = _myCertUiState.value.isEditMode

        _myCertUiState.update { it.copy(isEditMode = !isEditMode) }
    }

    fun editItem(id: Long) {
        val currentList = (_myCertUiState.value.myCertListLoadState as? UiState.Success)?.data
        val targetData = currentList?.find { it.certificationId == id }
        _myCertUiState.update {
            it.copy(editTargetCertification = targetData)
        }
    }

    fun closeEditSheet() {
        _myCertUiState.update { it.copy(editTargetCertification = null) }
    }

    fun openDeleteDialog(id: Long) {
        _myCertUiState.update { it.copy(deleteTargetId = id) }
    }

    fun closeDeleteDialog() {
        _myCertUiState.update { it.copy(deleteTargetId = null) }
    }

    fun deleteItem() {}
}
