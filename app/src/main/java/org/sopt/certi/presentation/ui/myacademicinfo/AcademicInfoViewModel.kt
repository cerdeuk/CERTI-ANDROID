package org.sopt.certi.presentation.ui.myacademicinfo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.sopt.certi.core.state.UiState
import org.sopt.certi.domain.type.CategoryType
import org.sopt.certi.domain.usecase.SearchMajorUseCase
import org.sopt.certi.domain.usecase.SearchUnivUseCase
import org.sopt.certi.presentation.ui.myacademicinfo.state.AcademicUiState
import org.sopt.certi.presentation.ui.myacademicinfo.state.EditSearchUiState
import javax.inject.Inject

@HiltViewModel
class AcademicInfoViewModel @Inject constructor(
    private val searchUnivUseCase: SearchUnivUseCase,
    private val searchMajorUseCase: SearchMajorUseCase
) : ViewModel() {
    private val _academicUiState = MutableStateFlow(AcademicUiState())
    val academicUiState = _academicUiState.asStateFlow()

    private val _editUnivUiState = MutableStateFlow(EditSearchUiState())
    val editUnivUiState = _editUnivUiState.asStateFlow()

    private val _editMajorUiState = MutableStateFlow(EditSearchUiState())
    val editMajorUiState = _editMajorUiState.asStateFlow()

    private val _editingCategoryList = MutableStateFlow<List<CategoryType>>(emptyList())
    val editingCategoryList = _editingCategoryList.asStateFlow()

    fun startCategoryEditing() {
        _editingCategoryList.value = _academicUiState.value.selectedCategoryList.toList()
    }

    fun editJobCategory(category: CategoryType) {
        val currentList = _editingCategoryList.value.toMutableList()
        if (currentList.contains(category)) {
            currentList.remove(category)
        } else {
            currentList.add(category)
        }
        _editingCategoryList.value = currentList
    }

    fun saveCategoryChanges() {
        _academicUiState.update {
            it.copy(selectedCategoryList = _editingCategoryList.value)
        }
    }

    fun getUnivList(univSearchText: String) {
        viewModelScope.launch {
            _editUnivUiState.update { it.copy(searchListLoadState = UiState.Loading) }
            searchUnivUseCase(univSearchText)
                .onSuccess { result ->
                    if (result.isEmpty()) {
                        _editUnivUiState.update { it.copy(searchListLoadState = UiState.Empty) }
                    } else {
                        _editUnivUiState.update { it.copy(searchListLoadState = UiState.Success(result)) }
                    }
                }.onFailure {
                    _editUnivUiState.update { it.copy(searchListLoadState = UiState.Failure(it.toString())) }
                }
        }
        _editUnivUiState.update { it.copy(searchText = univSearchText) }
    }

    fun onUnivSearchTextChange(univSearchText: String) {
        _editUnivUiState.update { it.copy(searchText = univSearchText) }
        if (univSearchText.isBlank()) {
            _editUnivUiState.update { it.copy(searchListLoadState = UiState.Init) }
        }
    }

    fun selectUniv(univName: String) {
        _editUnivUiState.update {
            it.copy(
                searchText = univName,
                submittedSearchText = univName
            )
        }
    }

    fun onUnivSaveClick() {}

    fun getMajorList(majorSearchText: String) {
        viewModelScope.launch {
            _editMajorUiState.update { it.copy(searchListLoadState = UiState.Loading) }
            searchMajorUseCase(majorSearchText)
                .onSuccess { result ->
                    if (result.isEmpty()) {
                        _editMajorUiState.update { it.copy(searchListLoadState = UiState.Empty) }
                    } else {
                        _editMajorUiState.update { it.copy(searchListLoadState = UiState.Success(result)) }
                    }
                }.onFailure {
                    _editMajorUiState.update { it.copy(searchListLoadState = UiState.Failure(it.toString())) }
                }
        }
        _editMajorUiState.update { it.copy(searchText = majorSearchText) }
    }

    fun onMajorSearchTextChange(majorSearchText: String) {
        _editMajorUiState.update { it.copy(searchText = majorSearchText) }
        if (majorSearchText.isBlank()) {
            _editMajorUiState.update { it.copy(searchListLoadState = UiState.Init) }
        }
    }

    fun selectMajor(majorName: String) {
        _editMajorUiState.update {
            it.copy(
                searchText = majorName,
                submittedSearchText = majorName
            )
        }
    }

    fun onMajorSaveClick() {}
}
