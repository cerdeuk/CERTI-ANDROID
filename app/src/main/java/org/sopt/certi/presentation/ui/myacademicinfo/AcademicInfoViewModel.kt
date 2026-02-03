package org.sopt.certi.presentation.ui.myacademicinfo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.sopt.certi.domain.type.CategoryType
import org.sopt.certi.domain.usecase.user.GetInterestedJobListUseCase
import org.sopt.certi.domain.usecase.user.ModifyInterestedJobListUseCase
import org.sopt.certi.presentation.ui.myacademicinfo.state.AcademicUiState
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class AcademicInfoViewModel @Inject constructor(
    private val getInterestedJobListUseCase: GetInterestedJobListUseCase,
    private val modifyInterestedJobListUseCase: ModifyInterestedJobListUseCase
) : ViewModel() {
    private val _academicUiState = MutableStateFlow(AcademicUiState())
    val academicUiState = _academicUiState.asStateFlow()

    private val _editingCategoryList = MutableStateFlow<List<CategoryType>>(emptyList())
    val editingCategoryList = _editingCategoryList.asStateFlow()

    init {
        getJobList()
    }

    private fun getJobList() = viewModelScope.launch {
        getInterestedJobListUseCase()
            .onSuccess { resultStringList ->
                val categoryList = resultStringList.jobList.mapNotNull { description ->
                    CategoryType.getByDescription(description)
                }
                _academicUiState.update { currentState ->
                    currentState.copy(selectedCategoryList = categoryList)
                }
            }
            .onFailure { error ->
                Timber.e(error, "Job List 불러오기 실패")
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

    fun saveCategoryChanges() = viewModelScope.launch {
        val request = _editingCategoryList.value.map { it.description }
        modifyInterestedJobListUseCase(request)
            .onSuccess {
                _academicUiState.update {
                    it.copy(selectedCategoryList = _editingCategoryList.value)
                }
            }
            .onFailure { error ->
                Timber.e(error, "희망분야 재설정 실패")
            }
    }
}
