package org.sopt.certi.presentation.ui.editpersonalinfo

import android.net.Uri
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.sopt.certi.domain.model.DateData
import org.sopt.certi.domain.model.user.PersonalInfo
import org.sopt.certi.domain.usecase.user.CheckNicknameValidationUseCase
import org.sopt.certi.domain.usecase.user.GetPersonalInfoUseCase
import org.sopt.certi.domain.usecase.user.PutPersonalInfoUseCase
import org.sopt.certi.presentation.type.NickNameValidType
import org.sopt.certi.presentation.ui.editpersonalinfo.state.EditPersonalInfoUiState
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class EditPersonalInfoViewModel @Inject constructor(
    private val getPersonalInfoUseCase: GetPersonalInfoUseCase,
    private val checkNicknameValidationUseCase: CheckNicknameValidationUseCase,
    private val putPersonalInfoUseCase: PutPersonalInfoUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(EditPersonalInfoUiState())
    val uiState = _uiState.asStateFlow()

    private val _nickNameValidTypeUiState = MutableStateFlow(NickNameValidType.DEFAULT)
    val nickNameValidTypeUiState = _nickNameValidTypeUiState.asStateFlow()

    private var _originalPersonalInfo: PersonalInfo? = null

    init {
        loadPersonalInfoData()
    }

    private fun loadPersonalInfoData() = viewModelScope.launch {
        _uiState.update { it.copy(isLoading = true) }

        getPersonalInfoUseCase()
            .onSuccess { result ->
                _originalPersonalInfo = result
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        name = result.name,
                        nickname = result.nickname,
                        email = result.email,
                        birth = result.birth,
                        profileUri = if (result.profileImageUrl.isNotEmpty()) result.profileImageUrl.toUri() else null
                    )
                }
            }
            .onFailure {
                _uiState.update { it.copy(isLoading = false) }
            }
    }

    private fun updateSaveButtonState() {
        val original = _originalPersonalInfo ?: return
        val current = _uiState.value
        val isNameChanged = current.name != original.name
        val isEmailChanged = current.email != original.email
        val isProfileChanged = current.profileUri?.toString() != original.profileImageUrl

        val isContentChanged = current.isNicknameChanged || isNameChanged || isEmailChanged || current.isBirthChanged || isProfileChanged

        val isNicknameValid = _nickNameValidTypeUiState.value in setOf(NickNameValidType.VALID, NickNameValidType.DEFAULT)

        _uiState.update {
            it.copy(isSaveButtonEnabled = isContentChanged && isNicknameValid && current.birth.isValid)
        }
    }

    fun onSaveClick() = viewModelScope.launch {
        val savedState = _uiState.value
        val request = PersonalInfo(
            name = savedState.name,
            nickname = savedState.nickname,
            email = savedState.email,
            birth = savedState.birth,
            profileImageUrl = savedState.profileUri?.toString() ?: ""
        )

        putPersonalInfoUseCase(request)
            .onSuccess {
                _nickNameValidTypeUiState.value = NickNameValidType.DEFAULT
                _uiState.update {
                    it.copy(
                        isNicknameChanged = false,
                        isBirthChanged = false,
                        isSaveButtonEnabled = false
                    )
                }
                _originalPersonalInfo = request
            }
            .onFailure {
                Timber.d("정보 업데이트 실패")
            }
    }

    fun onProfileUriChange(uri: Uri?) {
        _uiState.update { it.copy(profileUri = uri) }
        updateSaveButtonState()
    }

    fun onNickNameChange(nickname: String) {
        _uiState.update {
            it.copy(
                nickname = nickname,
                isNicknameChanged = _originalPersonalInfo?.nickname != null && nickname != _originalPersonalInfo?.nickname
            )
        }

        val original = _originalPersonalInfo ?: return

        _nickNameValidTypeUiState.update {
            when {
                nickname.isEmpty() -> NickNameValidType.EMPTY
                nickname == original.nickname -> NickNameValidType.DEFAULT
                else -> NickNameValidType.UNCHECKED
            }
        }

        updateSaveButtonState()
    }

    fun onNickNameCheckButtonClick() = viewModelScope.launch {
        checkNicknameValidationUseCase(_uiState.value.nickname)
            .onSuccess {
                _nickNameValidTypeUiState.value = NickNameValidType.VALID
                updateSaveButtonState()
            }
            .onFailure { throwable ->
                val msg = throwable.message

                _nickNameValidTypeUiState.value = when {
                    msg!!.contains("이미 존재하는 닉네임입니다.") -> NickNameValidType.DUPLICATE
                    msg.contains("닉네임에 비속어를 포함할 수 없습니다.") -> NickNameValidType.INVALID
                    else -> NickNameValidType.DEFAULT
                }
            }
    }

    fun onNameChange(name: String) {
        _uiState.update { it.copy(name = name) }
        updateSaveButtonState()
    }

    fun onEmailChange(email: String) {
        _uiState.update { it.copy(email = email) }
        updateSaveButtonState()
    }

    fun onBirthChange(birth: DateData) {
        _uiState.update {
            it.copy(
                birth = birth,
                isBirthChanged = _originalPersonalInfo?.birth != null && birth != _originalPersonalInfo?.birth
            )
        }
        updateSaveButtonState()
    }
}
