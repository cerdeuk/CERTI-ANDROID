package org.sopt.certi.presentation.ui.resume.state

import org.sopt.certi.core.state.UiState
import org.sopt.certi.domain.model.ActivityData
import org.sopt.certi.domain.model.certification.CertificationData
import org.sopt.certi.domain.model.user.UserInfoData

data class ResumeUiState(
    val userInfoLoadState: UiState<UserInfoData>,
    val acquiredCertificationListLoadState: UiState<List<CertificationData>>,
    val experienceListLoadState: UiState<List<ActivityData>>,
    val activityListLoadState: UiState<List<ActivityData>>,
    val selectedCertDetail: UiState<CertificationData>,
    val showCertificationDetailModal: Boolean = false
) {
    val loadState: UiState<Unit>
        get() = when {
            userInfoLoadState is UiState.Success &&
                acquiredCertificationListLoadState is UiState.Success &&
                experienceListLoadState is UiState.Success &&
                activityListLoadState is UiState.Success -> UiState.Success(Unit)

            userInfoLoadState is UiState.Failure ||
                acquiredCertificationListLoadState is UiState.Failure ||
                experienceListLoadState is UiState.Failure ||
                activityListLoadState is UiState.Failure -> UiState.Failure("fail to load data")

            userInfoLoadState is UiState.Loading ||
                acquiredCertificationListLoadState is UiState.Loading ||
                experienceListLoadState is UiState.Loading ||
                activityListLoadState is UiState.Loading -> UiState.Loading

            else -> UiState.Empty
        }
}
