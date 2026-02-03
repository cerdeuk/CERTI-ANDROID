package org.sopt.certi.presentation.ui.mypage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.sopt.certi.core.state.UiState
import org.sopt.certi.domain.usecase.user.GetMyPageUseCase
import org.sopt.certi.presentation.ui.mypage.state.MyPageUiSate
import javax.inject.Inject

@HiltViewModel
class MyPageMainViewModel@Inject constructor(
    private val myPageUseCase: GetMyPageUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(MyPageUiSate())
    val uiState = _uiState.asStateFlow()

    fun loadMyPageData() = viewModelScope.launch {
        myPageUseCase()
            .onSuccess { result ->
                _uiState.update {
                    it.copy(
                        myPageInfoLoadState = UiState.Success(Unit),
                        myPageInfo = result
                    )
                }
            }
            .onFailure {
                _uiState.update { it.copy(myPageInfoLoadState = UiState.Failure(it.toString())) }
            }
    }
}
