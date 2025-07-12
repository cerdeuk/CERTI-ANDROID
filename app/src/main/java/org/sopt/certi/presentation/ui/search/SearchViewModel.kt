package org.sopt.certi.presentation.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import org.sopt.certi.core.state.UiState
import org.sopt.certi.domain.model.CertificationData
import org.sopt.certi.domain.usecase.DummyUseCase
import org.sopt.certi.presentation.ui.search.state.SearchUiState
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val dummyUseCase: DummyUseCase
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
        val searchCertificationList = {
            listOf<CertificationData>(
                CertificationData(
                    certificationId = 1,
                    isFavorite = false,
                    certificationName = "정보처리기사",
                    tags = listOf("컴퓨터공학", "시각디자인", "경영"),
                    agencyName = "국가기술자격",
                    applicationMethod = "실기형"
                ),
                CertificationData(
                    certificationId = 2,
                    isFavorite = false,
                    certificationName = "정보처리",
                    tags = listOf("뿡뿡", "빵빵", "IT"),
                    agencyName = "한국정보처리기관",
                    applicationMethod = "실기형"
                ),
                CertificationData(
                    certificationId = 3,
                    isFavorite = false,
                    certificationName = "정보처리기사",
                    tags = listOf("컴퓨터공학", "시각디자인", "경영"),
                    agencyName = "국가기술자격",
                    applicationMethod = "실기형"
                ),
                CertificationData(
                    certificationId = 4,
                    isFavorite = false,
                    certificationName = "정보처리",
                    tags = listOf("뿡뿡", "빵빵", "IT"),
                    agencyName = "한국정보처리기관",
                    applicationMethod = "실기형"
                ),
                CertificationData(
                    certificationId = 5,
                    isFavorite = false,
                    certificationName = "정보처리기사",
                    tags = listOf("컴퓨터공학", "시각디자인", "경영"),
                    agencyName = "국가기술자격",
                    applicationMethod = "실기형"
                ),
                CertificationData(
                    certificationId = 6,
                    isFavorite = false,
                    certificationName = "정보처리",
                    tags = listOf("뿡뿡", "빵빵", "IT"),
                    agencyName = "한국정보처리기관",
                    applicationMethod = "실기형"
                ),
                CertificationData(
                    certificationId = 7,
                    isFavorite = false,
                    certificationName = "정보처리기사",
                    tags = listOf("컴퓨터공학", "시각디자인", "경영"),
                    agencyName = "국가기술자격",
                    applicationMethod = "실기형"
                ),
                CertificationData(
                    certificationId = 8,
                    isFavorite = false,
                    certificationName = "정보처리",
                    tags = listOf("뿡뿡", "빵빵", "IT"),
                    agencyName = "한국정보처리기관",
                    applicationMethod = "실기형"
                )
            )
        }
//        _searchLoadState.value = UiState.Success(searchCertificationList())
        _searchLoadState.value = UiState.Empty
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
