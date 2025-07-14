package org.sopt.certi.presentation.ui.resume.main.state

import org.sopt.certi.core.state.UiState
import org.sopt.certi.domain.model.ActivityData
import org.sopt.certi.domain.model.CertificationData

data class ResumeUiState(
    val jobCategoryLoadState: UiState<List<String>>,
    val acquiredCertificationListLoadState: UiState<List<CertificationData>>,
    val experienceListLoadState: UiState<List<ActivityData>>,
    val activityListLoadState: UiState<List<ActivityData>>
) {
    val loadState: UiState<Unit>
        get() = when {
            jobCategoryLoadState is UiState.Success &&
                acquiredCertificationListLoadState is UiState.Success &&
                experienceListLoadState is UiState.Success &&
                activityListLoadState is UiState.Success -> UiState.Success(Unit)
            jobCategoryLoadState is UiState.Failure &&
                acquiredCertificationListLoadState is UiState.Failure &&
                experienceListLoadState is UiState.Failure &&
                activityListLoadState is UiState.Failure -> UiState.Failure("fail to load data")
            jobCategoryLoadState is UiState.Loading &&
                acquiredCertificationListLoadState is UiState.Loading &&
                experienceListLoadState is UiState.Loading &&
                activityListLoadState is UiState.Loading -> UiState.Loading
            else -> UiState.Empty
        }
}
