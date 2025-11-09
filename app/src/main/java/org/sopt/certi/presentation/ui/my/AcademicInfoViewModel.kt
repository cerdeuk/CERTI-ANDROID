package org.sopt.certi.presentation.ui.my

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.sopt.certi.domain.type.CategoryType
import org.sopt.certi.presentation.ui.my.state.AcademicInfoUiState
import javax.inject.Inject

@HiltViewModel
class AcademicInfoViewModel @Inject constructor() : ViewModel() {
    private val _academicInfoUiState = MutableStateFlow(AcademicInfoUiState())
    val academicInfoUiState = _academicInfoUiState.asStateFlow()

    private val _editingCategoryList = MutableStateFlow<List<CategoryType>>(emptyList())
    val editingCategoryList = _editingCategoryList.asStateFlow()

    init {
        loadAcademicInfo()
    }

    private fun loadAcademicInfo() {
        viewModelScope.launch {
            val initialList = listOf(CategoryType.RND, CategoryType.MEDIA, CategoryType.CONSTRUCTION)
            _academicInfoUiState.update {
                AcademicInfoUiState(selectedCategoryList = initialList)
            }
        }
    }

    fun startEditing() {
        _editingCategoryList.value = _academicInfoUiState.value.selectedCategoryList.toList()
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
        _academicInfoUiState.update {
            it.copy(selectedCategoryList = _editingCategoryList.value)
        }
    }
}
