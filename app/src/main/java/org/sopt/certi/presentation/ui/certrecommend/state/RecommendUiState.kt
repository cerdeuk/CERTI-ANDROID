package org.sopt.certi.presentation.ui.certrecommend.state

import org.sopt.certi.core.state.UiState
import org.sopt.certi.domain.model.CertificationData

data class RecommendUiState(
    val recommendCertListLoadState: UiState<List<CertificationData>>,
    val jobListLoadState: UiState<List<String>>
) {
    val loadState: UiState<Unit>
        get() = when {
            jobListLoadState is UiState.Success &&
                recommendCertListLoadState is UiState.Success
            -> UiState.Success(Unit)

            jobListLoadState is UiState.Failure -> UiState.Failure(jobListLoadState.msg)
            recommendCertListLoadState is UiState.Failure -> UiState.Failure(recommendCertListLoadState.msg)

            jobListLoadState is UiState.Loading -> UiState.Loading

            else -> UiState.Empty
        }
}
