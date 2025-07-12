package org.sopt.certi.presentation.ui.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import org.sopt.certi.core.state.UiState
import org.sopt.certi.domain.usecase.DummyUseCase
import org.sopt.certi.presentation.ui.onboarding.state.OnBoardingMajorUiState
import org.sopt.certi.presentation.ui.onboarding.state.OnBoardingUnivUiState
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val dummyUseCase: DummyUseCase
) : ViewModel() {
    private val _onBoardingUnivLoadState = MutableStateFlow<UiState<List<String>>>(UiState.Init)
    private val _univSearchText = MutableStateFlow("")
    private val _submittedUnivSearchText = MutableStateFlow("")

    private val _onBoardingMajorLoadState = MutableStateFlow<UiState<List<String>>>(UiState.Init)
    private val _majorSearchText = MutableStateFlow("")
    private val _submittedMajorSearchText = MutableStateFlow("")

    val onBoardingUnivUiState: StateFlow<OnBoardingUnivUiState> =
        combine(
            _onBoardingUnivLoadState,
            _univSearchText,
            _submittedUnivSearchText
        ) { onBoardingUnivLoadState, univSearchText, submittedUnivSearchText ->
            OnBoardingUnivUiState(
                univSearchText = univSearchText,
                univListLoadState = onBoardingUnivLoadState,
                submittedUnivSearchText = submittedUnivSearchText
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = OnBoardingUnivUiState(
                univSearchText = "",
                univListLoadState = UiState.Init,
                submittedUnivSearchText = ""
            )
        )

    val onBoardingMajorUiState: StateFlow<OnBoardingMajorUiState> =
        combine(
            _onBoardingMajorLoadState,
            _majorSearchText,
            _submittedMajorSearchText
        ) { _onBoardingMajorLoadState, _majorSearchText, _submittedMajorSearchText ->
            OnBoardingMajorUiState(
                majorSearchText = _majorSearchText,
                majorListLoadState = _onBoardingMajorLoadState,
                submittedMajorSearchText = _submittedMajorSearchText
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = OnBoardingMajorUiState(
                majorSearchText = "",
                majorListLoadState = UiState.Init,
                submittedMajorSearchText = ""
            )
        )

    fun getUnivList(univSearchText: String) {
        _submittedUnivSearchText.value = univSearchText
        val univList = {
            listOf(
                "건국대학교",
                "홍익대학교",
                "응가대학교",
                "뿡뿡대학교",
                "건국대학교",
                "홍익대학교",
                "응가대학교",
                "뿡뿡대학교",
                "건국대학교",
                "홍익대학교",
                "응가대학교",
                "뿡뿡대학교"
            )
        }
        _onBoardingUnivLoadState.value = UiState.Success(univList())
    }

    fun onUnivSearchTextChange(univSearchText: String) {
        _univSearchText.value = univSearchText
        if (univSearchText.isBlank()) {
            _onBoardingUnivLoadState.value = UiState.Init
        }
    }

    fun selectUniv(univName: String) {
        _univSearchText.value = univName
        _submittedUnivSearchText.value = univName
    }

    fun getMajorList(majorSearchText: String) {
        _submittedMajorSearchText.value = majorSearchText
        val majorList = {
            listOf(
                "건국대학교",
                "홍익대학교",
                "응가대학교",
                "뿡뿡대학교",
                "건국대학교",
                "홍익대학교",
                "응가대학교",
                "뿡뿡대학교",
                "건국대학교",
                "홍익대학교",
                "응가대학교",
                "뿡뿡대학교"
            )
        }
        _onBoardingMajorLoadState.value = UiState.Success(majorList())
    }

    fun onMajorSearchTextChange(majorSearchText: String) {
        _majorSearchText.value = majorSearchText
        if (majorSearchText.isBlank()) {
            _onBoardingMajorLoadState.value = UiState.Init
        }
    }

    fun selectMajor(majorName: String) {
        _majorSearchText.value = majorName
        _submittedMajorSearchText.value = majorName
    }
}
