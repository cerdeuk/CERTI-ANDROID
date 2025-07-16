package org.sopt.certi.presentation.ui.activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.sopt.certi.core.state.UiState
import org.sopt.certi.domain.model.ActivityData
import org.sopt.certi.domain.usecase.DummyUseCase
import org.sopt.certi.presentation.ui.activity.sideEffect.ActivitySideEffect
import org.sopt.certi.presentation.ui.activity.state.ActivityUiState
import javax.inject.Inject

@HiltViewModel
class ActivityViewModel@Inject constructor(
    private val dummyUseCase: DummyUseCase
) : ViewModel() {
    private val _activityListLoadState = MutableStateFlow<UiState<List<ActivityData>>>(UiState.Loading)
    private val _selectedId = MutableStateFlow<Long?>(null)

    val activityUiState: StateFlow<ActivityUiState> =
        combine(
            _activityListLoadState,
            _selectedId
        ) { experienceList, selectedId ->
            ActivityUiState(
                activityListLoadState = experienceList,
                selectedId = selectedId
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = ActivityUiState(
                activityListLoadState = UiState.Loading,
                selectedId = null
            )
        )

    private val _sideEffect = Channel<ActivitySideEffect>()
    val sideEffect = _sideEffect.receiveAsFlow()

    fun getActivityList() {
        val resumeDataList = {
            emptyList<ActivityData>()
        }
        _activityListLoadState.value = UiState.Success(resumeDataList())
    }

    fun onDeleteClick(selectedId: Long) = viewModelScope.launch {
        _selectedId.value = selectedId
        _sideEffect.send(ActivitySideEffect.showDeleteDialog)
    }

    fun onDeleteConfirmclick() {
        val resumeDataList = {
            emptyList<ActivityData>()
        }
        _selectedId.value = null
        _activityListLoadState.value = UiState.Success(resumeDataList())
    }
}
