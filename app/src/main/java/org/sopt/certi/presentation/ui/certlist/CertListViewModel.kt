package org.sopt.certi.presentation.ui.certlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import org.sopt.certi.core.state.UiState
import org.sopt.certi.domain.model.certification.CertificationData
import org.sopt.certi.domain.usecase.DummyUseCase
import org.sopt.certi.presentation.ui.certlist.state.CertListUiState
import javax.inject.Inject

@HiltViewModel
class CertListViewModel @Inject constructor(
    private val dummyUseCase: DummyUseCase
) : ViewModel() {
    private val _certListLoadState = MutableStateFlow<UiState<List<CertificationData>>>(UiState.Loading)
    private val _selectedCategory = MutableStateFlow(0)
    private val _isFavorite = MutableStateFlow(false)

    val certificationListUiState: StateFlow<CertListUiState> =
        combine(_certListLoadState, _selectedCategory, _isFavorite) { certListLoadState, selectedCategory, isFavorite ->
            CertListUiState(
                certificationListLoadState = certListLoadState,
                selectedCategory = selectedCategory,
                isFavorite = isFavorite
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = CertListUiState(
                certificationListLoadState = UiState.Loading,
                selectedCategory = 0,
                isFavorite = false
            )
        )

    init {
        getCertificationList(isFavorite = false, category = 0)
    }

    fun getCertificationList(isFavorite: Boolean, category: Int) {
        val certificationList = {
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
        _certListLoadState.value = UiState.Success(certificationList())
    }

    fun onCategorySelected(index: Int) {
        _selectedCategory.value = index
    }

    fun onFavoriteClick() {
        _isFavorite.value = !_isFavorite.value
    }

    fun onLikeClick(certificationId: Long) {
        val currentState = _certListLoadState.value
        if (currentState is UiState.Success) {
            val updatedList = currentState.data.map {
                if (it.certificationId == certificationId) {
                    it.copy(isFavorite = !it.isFavorite)
                } else {
                    it
                }
            }
            _certListLoadState.value = UiState.Success(updatedList)
        }
    }
}
