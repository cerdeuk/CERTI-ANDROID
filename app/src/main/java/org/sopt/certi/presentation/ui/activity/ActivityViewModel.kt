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
import org.sopt.certi.domain.usecase.activity.DeleteActivityUseCase
import org.sopt.certi.domain.usecase.activity.GetActivityListUseCase
import org.sopt.certi.presentation.ui.activity.sideEffect.ActivitySideEffect
import org.sopt.certi.presentation.ui.activity.state.ActivityUiState
import javax.inject.Inject

@HiltViewModel
class ActivityViewModel@Inject constructor(
    private val getActivityListUseCase: GetActivityListUseCase,
    private val deleteActivityUseCase: DeleteActivityUseCase
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

    fun getActivityList() = viewModelScope.launch {
        _activityListLoadState.value = UiState.Loading
        getActivityListUseCase.invoke().fold(
            onSuccess = {
                val activityList = it
                _activityListLoadState.emit(UiState.Success(activityList))
            },
            onFailure = {
                _activityListLoadState.emit(UiState.Failure(it.message.toString()))
            }
        )
    }

    fun onDeleteClick(selectedId: Long) = viewModelScope.launch {
        _selectedId.value = selectedId
        _sideEffect.send(ActivitySideEffect.showDeleteDialog)
    }

    fun onDeleteConfirmClick() = viewModelScope.launch {
        _selectedId.value?.let {
            deleteActivityUseCase.invoke(it).fold(
                onSuccess = {
                    _selectedId.value = null
                    getActivityList()
                },
                onFailure = {
                    _selectedId.value = null
                }
            )
        }
    }

    fun onDeleteDismissClick() {
        _selectedId.value = null
    }
}
