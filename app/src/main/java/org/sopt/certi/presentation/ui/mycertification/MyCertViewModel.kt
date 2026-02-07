package org.sopt.certi.presentation.ui.mycertification

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.sopt.certi.core.state.UiState
import org.sopt.certi.domain.usecase.FavoriteUseCase
import org.sopt.certi.domain.usecase.PreCertUseCase
import org.sopt.certi.domain.usecase.ToggleFavoriteUseCase
import org.sopt.certi.domain.usecase.acquisition.DeleteAcquisitionUseCase
import org.sopt.certi.domain.usecase.acquisition.GetAcquisitionListUseCase
import org.sopt.certi.domain.usecase.acquisition.UpdateAcquisitionUseCase
import org.sopt.certi.domain.usecase.certification.DeletePreCertificationUseCase
import org.sopt.certi.domain.usecase.certification.UpdatePreCertificationUseCase
import org.sopt.certi.presentation.type.MyCertType
import org.sopt.certi.presentation.ui.mycertification.state.MyCertUiState
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MyCertViewModel @Inject constructor(
    private val preCertUseCase: PreCertUseCase,
    private val getAcquisitionListUseCase: GetAcquisitionListUseCase,
    private val favoriteUseCase: FavoriteUseCase,
    private val updatePreCertificationUseCase: UpdatePreCertificationUseCase,
    private val deletePreCertificationUseCase: DeletePreCertificationUseCase,
    private val updateAcquisitionUseCase: UpdateAcquisitionUseCase,
    private val deleteAcquisitionUseCase: DeleteAcquisitionUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase
) : ViewModel() {
    private val _myCertUiState = MutableStateFlow(MyCertUiState())
    val myCertUiState = _myCertUiState.asStateFlow()

    init {
        getPlannedCertificationList()
    }

    fun updateSelectedTab(tabType: MyCertType) {
        if (_myCertUiState.value.isEditMode) return
        if (_myCertUiState.value.selectedTab == tabType) return

        _myCertUiState.update {
            it.copy(
                selectedTab = tabType
            )
        }

        when (tabType) {
            MyCertType.PLANNED -> getPlannedCertificationList()
            MyCertType.ACQUIRED -> getAcquiredCertificationList()
            MyCertType.FAVORITE -> getFavoriteCertificationList()
        }
    }

    private fun getCertificationList() {
        when (_myCertUiState.value.selectedTab) {
            MyCertType.PLANNED -> getPlannedCertificationList()
            MyCertType.ACQUIRED -> getAcquiredCertificationList()
            MyCertType.FAVORITE -> getFavoriteCertificationList()
        }
    }

    private fun getPlannedCertificationList() = viewModelScope.launch {
        preCertUseCase()
            .onSuccess { result ->
                _myCertUiState.update {
                    it.copy(plannedCertListState = if (result.isEmpty()) UiState.Empty else UiState.Success(result))
                }
            }
            .onFailure { error ->
                Timber.e(error, "취득 예정 자격증 불러오기 실패")
                _myCertUiState.update { it.copy(plannedCertListState = UiState.Failure(error.message ?: "Failed")) }
            }
    }

    private fun getAcquiredCertificationList() = viewModelScope.launch {
        getAcquisitionListUseCase()
            .onSuccess { result ->
                _myCertUiState.update {
                    it.copy(acquiredCertListState = if (result.isEmpty()) UiState.Empty else UiState.Success(result))
                }
            }
            .onFailure { error ->
                Timber.e(error, "취득 완료 자격증 불러오기 실패")
                _myCertUiState.update { it.copy(acquiredCertListState = UiState.Failure(error.message ?: "Failed")) }
            }
    }

    private fun getFavoriteCertificationList() = viewModelScope.launch {
        favoriteUseCase()
            .onSuccess { result ->
                _myCertUiState.update {
                    it.copy(favoriteCertListState = if (result.isEmpty()) UiState.Empty else UiState.Success(result))
                }
            }
            .onFailure { error ->
                Timber.e(error, "즐겨찾기 자격증 불러오기 실패")
                _myCertUiState.update { it.copy(favoriteCertListState = UiState.Failure(error.message ?: "Failed")) }
            }
    }

    fun onFavoriteToggle(id: Long) = viewModelScope.launch {
        toggleFavoriteUseCase(id)
            .onSuccess {
                getFavoriteCertificationList()
            }
            .onFailure { error ->
                Timber.e(error, "즐겨찾기 취소 실패")
            }
    }

    fun onEditModeToggle() {
        val isEditMode = _myCertUiState.value.isEditMode

        _myCertUiState.update { it.copy(isEditMode = !isEditMode) }
    }

    fun onEditClick(id: Long) {
        val currentState = _myCertUiState.value
        val currentList = (currentState.currentListState as? UiState.Success)?.data ?: emptyList()
        val targetData = currentList.find { it.certificationId == id }

        Timber.d("Edit Certification: $targetData")

        _myCertUiState.update {
            it.copy(editTargetCertification = targetData)
        }
    }

    fun editPreCertification(city: String, state: String, timeData: String) = viewModelScope.launch {
        val editCertificationId = _myCertUiState.value.editTargetCertification?.preCertificationId
        editCertificationId?.let {
            updatePreCertificationUseCase(editCertificationId, timeData, city, state)
                .onSuccess {
                    getPlannedCertificationList()
                    closeEditSheet()
                }
                .onFailure { error ->
                    Timber.e(error, "취득 예정 자격증 수정 실패")
                }
        }
    }

    fun editAcquisitionCertification(acquisitionDate: String, grade: String) = viewModelScope.launch {
        _myCertUiState.value.editTargetCertification?.acquisitionId?.let { acquisitionId ->
            updateAcquisitionUseCase(acquisitionId, acquisitionDate, grade)
                .onSuccess {
                    getAcquiredCertificationList()
                    closeEditSheet()
                }
                .onFailure { error ->
                    Timber.e(error, "취득 완료 자격증 수정 실패")
                }
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

    fun deleteItem() = viewModelScope.launch {
        val targetListId = _myCertUiState.value.deleteTargetId ?: return@launch

        val currentList = (_myCertUiState.value.currentListState as? UiState.Success)?.data
        val targetItem = currentList?.find { it.certificationId == targetListId }

        targetItem?.let { data ->
            when (_myCertUiState.value.selectedTab) {
                MyCertType.PLANNED -> {
                    deletePreCertificationUseCase(data.certificationId)
                        .onSuccess {
                            _myCertUiState.update { it.copy(deleteTargetId = null) }
                            getPlannedCertificationList()
                        }
                        .onFailure { error ->
                            Timber.e(error, "취득 예정 자격증 삭제 실패")
                        }
                }
                MyCertType.ACQUIRED -> {
                    data.acquisitionId?.let { id ->
                        deleteAcquisitionUseCase(id)
                            .onSuccess {
                                _myCertUiState.update { it.copy(deleteTargetId = null) }
                                getCertificationList()
                            }
                            .onFailure { error ->
                                Timber.e(error, "취득 완료 자격증 삭제 실패")
                            }
                    }
                }
                MyCertType.FAVORITE -> {}
            }
        }
    }
}
