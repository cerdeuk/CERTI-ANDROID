package org.sopt.certi.presentation.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.sopt.certi.core.state.UiState
import org.sopt.certi.domain.model.certification.CertificationData
import org.sopt.certi.domain.usecase.certification.SearchCertListUseCase
import org.sopt.certi.presentation.ui.search.state.SearchUiState
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchCertListUseCase: SearchCertListUseCase
) : ViewModel() {
    private val _searchLoadState = MutableStateFlow<UiState<List<CertificationData>>>(UiState.Init)
    private val _keyword = MutableStateFlow("")
    private val _submittedKeyword = MutableStateFlow("")

    val searchListUiState: StateFlow<SearchUiState> =
        combine(
            _searchLoadState,
            _keyword,
            _submittedKeyword
        ) { searchLoadState, keyword, submittedKeyword ->
            SearchUiState(
                searchCertificationListLoadState = searchLoadState,
                keyword = keyword,
                submittedKeyword = submittedKeyword
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = SearchUiState(
                searchCertificationListLoadState = UiState.Init,
                keyword = "",
                submittedKeyword = ""
            )
        )

    fun getSearchCertificationList(keyword: String) {
        _submittedKeyword.value = keyword
        if (keyword.isBlank()) {
            _searchLoadState.value = UiState.Empty
            return
        }

        _searchLoadState.value = UiState.Loading

        viewModelScope.launch {
            searchCertListUseCase(keyword)
                .onSuccess { result ->
                    if (result.isEmpty()) {
                        _searchLoadState.value = UiState.Empty
                    } else {
                        _searchLoadState.value = UiState.Success(result)
                    }
                }
                .onFailure { e ->
                    _searchLoadState.value = UiState.Failure(e.toString())
                }
        }
    }

    fun onKeywordChange(keyword: String) {
        _keyword.value = keyword
        if (keyword.isBlank()) {
            _searchLoadState.value = UiState.Init
        }
    }

    fun onLikeClick(certificationId: Long) {
        val currentState = _searchLoadState.value
        if (currentState is UiState.Success) {
            val updated = currentState.data.map {
                if (it.certificationId == certificationId) {
                    it.copy(isFavorite = !it.isFavorite)
                } else {
                    it
                }
            }
            _searchLoadState.value = UiState.Success(updated)
        }
    }
}
