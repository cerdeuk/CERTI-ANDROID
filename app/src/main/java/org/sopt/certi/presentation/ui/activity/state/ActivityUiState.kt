package org.sopt.certi.presentation.ui.activity.state

import org.sopt.certi.core.state.UiState
import org.sopt.certi.domain.model.ActivityData

data class ActivityUiState(
    val activityListLoadState: UiState<List<ActivityData>>,
    val selectedId: Long?
) {
    val loadState: UiState<Unit>
        get() = when (activityListLoadState) {
            is UiState.Success -> UiState.Success(Unit)
            is UiState.Failure -> UiState.Failure("fail to load data")
            is UiState.Loading -> UiState.Loading
            else -> UiState.Empty
        }
}
