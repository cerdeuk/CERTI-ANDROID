package org.sopt.certi.presentation.ui.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.sopt.certi.core.network.TokenManager
import org.sopt.certi.core.state.UiState
import org.sopt.certi.domain.model.UserInfoData
import org.sopt.certi.domain.usecase.SearchMajorUseCase
import org.sopt.certi.domain.usecase.SearchUnivUseCase
import org.sopt.certi.domain.usecase.SignUpUseCase
import org.sopt.certi.presentation.ui.onboarding.state.OnBoardingMajorUiState
import org.sopt.certi.presentation.ui.onboarding.state.OnBoardingUnivUiState
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase,
    private val tokenManager: TokenManager,
    private val searchUnivUseCase: SearchUnivUseCase,
    private val searchMajorUseCase: SearchMajorUseCase
) : ViewModel() {
    private val _onBoardingUnivLoadState = MutableStateFlow<UiState<List<String>>>(UiState.Init)
    private val _univSearchText = MutableStateFlow("")
    private val _submittedUnivSearchText = MutableStateFlow("")

    private val _onBoardingMajorLoadState = MutableStateFlow<UiState<List<String>>>(UiState.Init)
    private val _majorSearchText = MutableStateFlow("")
    private val _submittedMajorSearchText = MutableStateFlow("")

    private val _grade = MutableStateFlow<String?>(null)
    val grade: StateFlow<String?> = _grade.asStateFlow()

    private val _track = MutableStateFlow<String?>(null)
    val track: StateFlow<String?> = _track.asStateFlow()

    private val _jobCategory = MutableStateFlow<List<String>>(emptyList())
    val jobCategory: StateFlow<List<String>> = _jobCategory.asStateFlow()

    private val _userInfo = MutableStateFlow<UserInfoData?>(null)
    val userInfo: StateFlow<UserInfoData?> = _userInfo.asStateFlow()

    private val _signUpSuccess = MutableStateFlow(false)
    val signUpSuccess: StateFlow<Boolean> = _signUpSuccess.asStateFlow()

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
        ) { onBoardingMajorLoadState, majorSearchText, submittedMajorSearchText ->
            OnBoardingMajorUiState(
                majorSearchText = majorSearchText,
                majorListLoadState = onBoardingMajorLoadState,
                submittedMajorSearchText = submittedMajorSearchText
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
        viewModelScope.launch {
            _onBoardingUnivLoadState.value = UiState.Loading
            searchUnivUseCase(univSearchText)
                .onSuccess { result ->
                    if (result.isEmpty()) {
                        _onBoardingUnivLoadState.value = UiState.Empty
                    } else {
                        _onBoardingUnivLoadState.value = UiState.Success(result)
                    }
                }.onFailure {
                    _onBoardingUnivLoadState.value = UiState.Failure(it.toString())
                }
        }
        _submittedUnivSearchText.value = univSearchText
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
        viewModelScope.launch {
            _onBoardingMajorLoadState.value = UiState.Loading
            searchMajorUseCase(majorSearchText)
                .onSuccess { result ->
                    if (result.isEmpty()) {
                        _onBoardingMajorLoadState.value = UiState.Empty
                    } else {
                        _onBoardingMajorLoadState.value = UiState.Success(result)
                    }
                }.onFailure {
                    _onBoardingMajorLoadState.value = UiState.Failure(it.toString())
                }
        }
        _submittedMajorSearchText.value = majorSearchText
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

    fun onGradeSelected(grade: String?) {
        _grade.value = grade
    }

    fun onTrackSelected(track: String?) {
        _track.value = track
    }

    fun onJobCategoryChanged(selected: List<String>) {
        _jobCategory.value = selected
    }

    fun postSignUp() {
        viewModelScope.launch {
            val preSignUpToken = tokenManager.getPreSignupToken()
            val userInfo = tokenManager.getUserInformation()

            signUpUseCase(
                preSignupToken = preSignUpToken,
                userInformation = userInfo,
                university = _submittedUnivSearchText.value,
                grade = grade.value.toString(),
                track = track.value.toString(),
                major = _submittedMajorSearchText.value,
                jobs = jobCategory.value
            ).fold(
                onSuccess = { signUpResult ->
                    _userInfo.value = UserInfoData(
                        name = signUpResult.nickName,
                        university = signUpResult.university,
                        major = signUpResult.major,
                        category = signUpResult.jobs,
                        track = signUpResult.trackType
                    )
                    tokenManager.saveToken(signUpResult.jwtResponse.accessToken)
                    tokenManager.saveRefreshToken(signUpResult.jwtResponse.refreshToken)
                    _signUpSuccess.value = true
                },
                onFailure = {
                    _signUpSuccess.value = false
                }
            )
        }
    }
}
