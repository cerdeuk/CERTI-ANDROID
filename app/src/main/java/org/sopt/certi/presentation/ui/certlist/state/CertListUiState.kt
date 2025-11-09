package org.sopt.certi.presentation.ui.certlist.state

import kotlinx.collections.immutable.ImmutableList
import org.sopt.certi.core.state.UiState
import org.sopt.certi.domain.model.certification.CertificationData

data class CertListUiState(
    val recommendListLoadState: UiState<ImmutableList<CertificationData>> = UiState.Loading,
    val trackTop3ListLoadState: UiState<ImmutableList<CertificationData>> = UiState.Loading,
    val categoryTop3ListLoadState: UiState<ImmutableList<CertificationData>> = UiState.Loading
) {
    val loadState: UiState<Unit>
        get() = when {
            recommendListLoadState is UiState.Loading &&
                trackTop3ListLoadState is UiState.Loading &&
                categoryTop3ListLoadState is UiState.Loading -> UiState.Loading

            recommendListLoadState is UiState.Failure ||
                trackTop3ListLoadState is UiState.Failure ||
                categoryTop3ListLoadState is UiState.Failure -> UiState.Failure("fail to load data")

            recommendListLoadState is UiState.Success &&
                trackTop3ListLoadState is UiState.Success &&
                categoryTop3ListLoadState is UiState.Success -> UiState.Success(Unit)

            else -> UiState.Loading
        }
}
