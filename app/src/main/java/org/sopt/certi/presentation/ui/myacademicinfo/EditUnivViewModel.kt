package org.sopt.certi.presentation.ui.myacademicinfo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.sopt.certi.core.state.UiState
import org.sopt.certi.domain.usecase.auth.SearchUnivUseCase
import org.sopt.certi.domain.usecase.user.PutUniversityUseCase
import org.sopt.certi.presentation.ui.myacademicinfo.state.EditSearchUiState
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class EditUnivViewModel @Inject constructor(
    private val searchUnivUseCase: SearchUnivUseCase,
    private val putUniversityUseCase: PutUniversityUseCase
) : ViewModel() {
    private val _editUnivUiState = MutableStateFlow(EditSearchUiState())
    val editUnivUiState = _editUnivUiState.asStateFlow()

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

    fun onUnivSaveClick() = viewModelScope.launch {
        val submittedText = _editUnivUiState.value.submittedSearchText
        putUniversityUseCase(submittedText)
            .onSuccess {
                _editUnivUiState.update {
                    it.copy(
                        initialValue = submittedText
                    )
                }
            }
            .onFailure { error ->
                Timber.e(error, "대학교 변경 실패")
            }
    }
}
