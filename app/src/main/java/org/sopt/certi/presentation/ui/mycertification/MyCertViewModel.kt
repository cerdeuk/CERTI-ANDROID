package org.sopt.certi.presentation.ui.mycertification

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.sopt.certi.core.state.UiState
import org.sopt.certi.presentation.type.MyCertType
import org.sopt.certi.presentation.ui.mycertification.state.MyCertUiState
import javax.inject.Inject

@HiltViewModel
class MyCertViewModel @Inject constructor() : ViewModel() {
    private val _myCertUiState = MutableStateFlow(
        MyCertUiState(
            selectedTab = MyCertType.PLANNED,
            myCertListLoadState = UiState.Loading,
            selectedCertificationId = null
        )
    )
    val myCertUiState = _myCertUiState.asStateFlow()

    init {
        getMyCertList()
    }

    fun updateSelectedTab(tabType: MyCertType) {
        if (_myCertUiState.value.selectedTab == tabType) return

        _myCertUiState.update {
            it.copy(selectedTab = tabType)
        }
        getMyCertList()
    }

    fun getMyCertList() = viewModelScope.launch {
        _myCertUiState.update {
            it.copy(myCertListLoadState = UiState.Success(dummyCertifications))
        }
    }
}
