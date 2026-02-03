package org.sopt.certi.presentation.ui.myacademicinfo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.sopt.certi.core.state.UiState
import org.sopt.certi.domain.usecase.auth.SearchMajorUseCase
import org.sopt.certi.domain.usecase.user.PutMajorUseCase
import org.sopt.certi.presentation.ui.myacademicinfo.state.EditSearchUiState
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class EditMajorViewModel @Inject constructor(
    private val searchMajorUseCase: SearchMajorUseCase,
    private val putMajorUseCase: PutMajorUseCase
) : ViewModel() {
    private val _editMajorUiState = MutableStateFlow(EditSearchUiState())
    val editMajorUiState = _editMajorUiState.asStateFlow()

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

    fun onMajorSaveClick() = viewModelScope.launch {
        val submittedText = _editMajorUiState.value.submittedSearchText
        putMajorUseCase(submittedText)
            .onSuccess {
                _editMajorUiState.update {
                    it.copy(
                        initialValue = submittedText
                    )
                }
            }
            .onFailure { error ->
                Timber.e(error, "학과 변경 실패")
            }
    }
}
