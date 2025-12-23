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
import org.sopt.certi.presentation.type.AcademicInfoType
import org.sopt.certi.presentation.ui.myacademicinfo.state.AcademicUiState
import org.sopt.certi.presentation.ui.myacademicinfo.state.EditSearchUiState
import javax.inject.Inject

@HiltViewModel
class AcademicInfoViewModel @Inject constructor() : ViewModel() {
    private val _academicUiState = MutableStateFlow(AcademicUiState())
    val academicUiState = _academicUiState.asStateFlow()

    private val _editUnivUiState = MutableStateFlow(EditSearchUiState())
    val editUnivUiState = _editUnivUiState.asStateFlow()

    private val _editMajorUiState = MutableStateFlow(EditSearchUiState())
    val editMajorUiState = _editMajorUiState.asStateFlow()

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
            _editUnivUiState.update {
                it.copy(
                    searchText = "기존대학교",
                    submittedSearchText = "기존대학교",
                    savedText = "기존대학교"
                )
            }
        }
    }

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

    private fun getTargetStateFlow(type: AcademicInfoType): MutableStateFlow<EditSearchUiState> {
        return when (type) {
            AcademicInfoType.UNIV -> _editUnivUiState
            AcademicInfoType.MAJOR -> _editMajorUiState
        }
    }

    fun onSearchTextChanged(type: AcademicInfoType, text: String) {
        val targetState = getTargetStateFlow(type)
        targetState.update { it.copy(searchText = text) }
    }

    fun onSearchClick(type: AcademicInfoType) {
        val targetState = getTargetStateFlow(type)
        if (targetState.value.searchText.isEmpty()) return

        viewModelScope.launch {
            val resultList = when (type) {
                AcademicInfoType.UNIV -> listOf("서울대학교", "연세대학교", "고려대학교")
                AcademicInfoType.MAJOR -> listOf("컴퓨터공학과", "경영학과", "시각디자인학과")
            }

            targetState.update {
                it.copy(searchListLoadState = UiState.Success(resultList))
            }
        }
    }

    fun onItemSelected(type: AcademicInfoType, itemName: String) {
        val targetState = getTargetStateFlow(type)
        targetState.update {
            it.copy(
                searchText = itemName,
                submittedSearchText = itemName
            )
        }
    }

    fun onSaveClick(type: AcademicInfoType) {
        val targetState = getTargetStateFlow(type)
        targetState.update {
            it.copy(savedText = targetState.value.submittedSearchText)
        }
    }
}
