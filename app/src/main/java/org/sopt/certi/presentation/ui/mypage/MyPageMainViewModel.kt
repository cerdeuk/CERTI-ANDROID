package org.sopt.certi.presentation.ui.mypage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.sopt.certi.presentation.ui.mypage.state.MyPageUiSate
import javax.inject.Inject

class MyPageMainViewModel@Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(MyPageUiSate("", "", emptyList(), 0, 0, 0))
    val uiState = _uiState.asStateFlow()

    init {
        loadMyPageData()
    }

    private fun loadMyPageData() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    name = "김서티",
                    email = "certification@gmail.com",
                    jobList = listOf("경영/사무", "무역/유통", "마케팅/광고/홍보")
                )
            }
        }
    }
}
