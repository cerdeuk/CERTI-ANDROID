package org.sopt.certi.presentation.ui.editacademicinfo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.sopt.certi.core.state.UiState
import org.sopt.certi.domain.type.CategoryType
import org.sopt.certi.presentation.ui.editacademicinfo.state.AcademicUiState
import org.sopt.certi.presentation.ui.editacademicinfo.state.EditUnivNameUiState
import javax.inject.Inject

@HiltViewModel
class AcademicInfoViewModel @Inject constructor() : ViewModel() {
    private val _academicUiState = MutableStateFlow(AcademicUiState())
    val academicUiState = _academicUiState.asStateFlow()

    private val _editUnivNameUiState = MutableStateFlow(EditUnivNameUiState())
    val editUnivUiState = _editUnivNameUiState.asStateFlow()

    private val _editingCategoryList = MutableStateFlow<List<CategoryType>>(emptyList())
    val editingCategoryList = _editingCategoryList.asStateFlow()

    init {
        loadAcademicInfo()
    }

    private fun loadAcademicInfo() {
        viewModelScope.launch {
            val initialList = listOf(CategoryType.RND, CategoryType.MEDIA, CategoryType.CONSTRUCTION)
            _academicUiState.update {
                AcademicUiState(selectedCategoryList = initialList)
            }
            _editUnivNameUiState.update {
                it.copy(
                    univSearchText = "기존대학교",
                    submittedUnivSearchText = "기존대학교",
                    savedUnivName = "기존대학교"
                )
            }
        }
    }

    fun startEditing() {
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

    fun saveChanges() {
        _academicUiState.update {
            it.copy(selectedCategoryList = _editingCategoryList.value)
        }
    }

    fun onUnivSearchTextChanged(text: String) {
        _editUnivNameUiState.update {
            it.copy(univSearchText = text)
        }
    }

    fun onSearchUnivClick() {
        if (_editUnivNameUiState.value.univSearchText.isEmpty()) return

        viewModelScope.launch {
            _editUnivNameUiState.update {
                it.copy(univListLoadState = UiState.Success(listOf("서울대학교", "연세대학교", "고려대학교")))
            }
        }
    }

    fun onUnivSelected(univName: String) {
        _editUnivNameUiState.update {
            it.copy(
                univSearchText = univName,
                submittedUnivSearchText = univName
            )
        }
    }

    fun onUnivSaveClick() {
        _editUnivNameUiState.update {
            it.copy(savedUnivName = _editUnivNameUiState.value.submittedUnivSearchText)
        }
    }
}
