package org.sopt.certi.presentation.ui.certlist

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
import org.sopt.certi.domain.type.CategoryType
import org.sopt.certi.domain.usecase.ToggleFavoriteUseCase
import org.sopt.certi.domain.usecase.certification.GetCategoryCertListUseCase
import org.sopt.certi.presentation.ui.certlist.state.CertListUiState
import javax.inject.Inject

@HiltViewModel
class CertListViewModel @Inject constructor(
    private val getCategoryCertListUseCase: GetCategoryCertListUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase
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

    fun getCertificationList(isFavorite: Boolean, category: Int) {
        viewModelScope.launch {
            _certListLoadState.value = UiState.Loading
            if (category == 0) {
                getRecommendCertificationList()
            } else {
                getCategoryCertificationList(isFavorite, category)
            }
        }
    }

    private fun getRecommendCertificationList() = viewModelScope.launch {
        _certListLoadState.value = UiState.Loading
        // 맞춤 추천 서버통신 로직
    }

    private fun getCategoryCertificationList(isFavorite: Boolean, category: Int) = viewModelScope.launch {
        _certListLoadState.value = UiState.Loading
        val jobs = CategoryType.entries.getOrNull(category - 1)?.description ?: ""
        getCategoryCertListUseCase(isFavorite, jobs)
            .onSuccess { list -> _certListLoadState.value = if (list.isEmpty()) UiState.Empty else UiState.Success(list) }
            .onFailure { _certListLoadState.value = UiState.Failure(it.toString()) }
    }

    fun onCategorySelected(index: Int) {
        _selectedCategory.value = index
    }

    fun onFavoriteClick() {
        _isFavorite.value = !_isFavorite.value
    }

    fun onLikeClick(certificationId: Long) {
        viewModelScope.launch {
            toggleFavoriteUseCase(certificationId)
                .onSuccess {
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
                .onFailure {
                    _certListLoadState.value = UiState.Failure(it.toString())
                }
        }
    }
}
