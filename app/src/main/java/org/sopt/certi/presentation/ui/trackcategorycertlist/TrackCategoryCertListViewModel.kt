package org.sopt.certi.presentation.ui.trackcategorycertlist

import androidx.lifecycle.SavedStateHandle
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
import org.sopt.certi.domain.type.TrackType
import org.sopt.certi.domain.usecase.ToggleFavoriteUseCase
import org.sopt.certi.domain.usecase.certification.GetJobCertListUseCase
import org.sopt.certi.domain.usecase.certification.GetTrackCertListUseCase
import org.sopt.certi.presentation.ui.trackcategorycertlist.state.TrackCategoryCertListUiState
import org.sopt.certi.presentation.ui.trackcategorycertlist.model.TrackCategoryType
import javax.inject.Inject

@HiltViewModel
class TrackCategoryCertListViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getJobCertListUseCase: GetJobCertListUseCase,
    private val getTrackCertListUseCase: GetTrackCertListUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase
) : ViewModel() {
    val mode: TrackCategoryType =
        TrackCategoryType.fromRouteArg(savedStateHandle.get<String>("mode"))
    private val default: String = savedStateHandle.get<String>("default").orEmpty()

    private val _certListLoadState = MutableStateFlow<UiState<List<CertificationData>>>(UiState.Loading)
    private val _selectedCategory = MutableStateFlow(0)
    private val _isFavorite = MutableStateFlow(false)

    val certificationListUiState: StateFlow<TrackCategoryCertListUiState> =
        combine(_certListLoadState, _selectedCategory, _isFavorite) { certListLoadState, selectedCategory, isFavorite ->
            TrackCategoryCertListUiState(
                certificationListLoadState = certListLoadState,
                selectedCategory = selectedCategory,
                isFavorite = isFavorite
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = TrackCategoryCertListUiState(
                certificationListLoadState = UiState.Loading,
                selectedCategory = 0,
                isFavorite = false
            )
        )

    init {
        _selectedCategory.value = when (mode) {
            TrackCategoryType.CATEGORY ->
                CategoryType.getByDescription(default)?.ordinal ?: 0
            TrackCategoryType.TRACK ->
                TrackType.getByDescription(default)?.ordinal ?: 0
        }
    }

    fun getCertificationList(
        isFavorite: Boolean,
        category: Int
    ) {
        viewModelScope.launch {
            _certListLoadState.value = UiState.Loading
            when (mode) {
                TrackCategoryType.CATEGORY -> getCategoryCertificationList(isFavorite, category)
                TrackCategoryType.TRACK -> getTrackCertificationList(isFavorite, category)
            }
        }
    }

    private fun getCategoryCertificationList(
        isFavorite: Boolean,
        category: Int
    ) = viewModelScope.launch {
        _certListLoadState.value = UiState.Loading
        val jobs = CategoryType.entries.getOrNull(category)?.description ?: ""
        getJobCertListUseCase(isFavorite, jobs)
            .onSuccess { list -> _certListLoadState.value = if (list.isEmpty()) UiState.Empty else UiState.Success(list) }
            .onFailure { _certListLoadState.value = UiState.Failure(it.toString()) }
    }

    private fun getTrackCertificationList(
        isFavorite: Boolean,
        track: Int
    ) = viewModelScope.launch {
        _certListLoadState.value = UiState.Loading
        val tracks = TrackType.entries.getOrNull(track)?.description ?: ""
        getTrackCertListUseCase(isFavorite, tracks)
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
