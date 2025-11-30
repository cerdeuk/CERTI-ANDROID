package org.sopt.certi.presentation.ui.personalInfo

import android.net.Uri
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.sopt.certi.domain.model.DateData
import org.sopt.certi.domain.model.user.UserProfile
import org.sopt.certi.presentation.type.NickNameValidType
import org.sopt.certi.presentation.ui.personalInfo.state.PersonalInfoUiState
import javax.inject.Inject

class PersonalInfoViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(PersonalInfoUiState())
    val uiState = _uiState.asStateFlow()

    private val _nickNameValidTypeUiState = MutableStateFlow(NickNameValidType.DEFAULT)
    val nickNameValidTypeUiState = _nickNameValidTypeUiState.asStateFlow()

    private lateinit var _originalUserProfile: UserProfile

    init {
        loadPersonalInfoData()
    }

    private fun loadPersonalInfoData() {
        viewModelScope.launch {
            val profileData = UserProfile(
                nickname = "nick",
                name = "name",
                email = "certification@gmail.com",
                birth = DateData(2004, 5, 31),
                profileImageUrl = null
            )

            _originalUserProfile = profileData
            _uiState.update {
                PersonalInfoUiState(
                    nickname = profileData.nickname,
                    name = profileData.name,
                    email = profileData.email,
                    birth = profileData.birth,
                    profileUri = profileData.profileImageUrl?.toUri()
                )
            }
        }
    }

    private fun updateSaveButtonState() {
        val current = _uiState.value

        val isContentChanged =
            current.isNicknameChanged ||
                current.name != _originalUserProfile.name ||
                current.email != _originalUserProfile.email ||
                (current.birth != _originalUserProfile.birth && current.birth.isValid) ||
                current.profileUri?.toString() != _originalUserProfile.profileImageUrl

        val isNicknameValid = _nickNameValidTypeUiState.value in setOf(NickNameValidType.VALID, NickNameValidType.DEFAULT)

        _uiState.update {
            it.copy(isSaveButtonEnabled = isContentChanged && isNicknameValid)
        }
    }

    fun onSaveClick() {
        viewModelScope.launch {
            _nickNameValidTypeUiState.value = NickNameValidType.DEFAULT
            val savedState = _uiState.value
            _uiState.update {
                it.copy(
                    isNicknameChanged = false,
                    isBirthChanged = false,
                    isSaveButtonEnabled = false
                )
            }
            _originalUserProfile = UserProfile(
                name = savedState.name,
                nickname = savedState.nickname,
                email = savedState.email,
                birth = savedState.birth,
                profileImageUrl = savedState.profileUri?.toString() // 서버 url로 나중에 수정
            )
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
                isNicknameChanged = nickname != _originalUserProfile.nickname
            )
        }
        _nickNameValidTypeUiState.update {
            when {
                nickname.isEmpty() -> NickNameValidType.EMPTY
                nickname.contains("시발") -> NickNameValidType.INVALID
                nickname == _originalUserProfile.nickname -> NickNameValidType.DEFAULT
                else -> NickNameValidType.UNCHECKED
            }
        }
        updateSaveButtonState()
    }

    fun onNickNameCheckButtonClick() {
        _nickNameValidTypeUiState.update {
            when {
                (_nickNameValidTypeUiState.value == NickNameValidType.UNCHECKED) -> NickNameValidType.VALID
                else -> NickNameValidType.DUPLICATE
            }
        }
        updateSaveButtonState()
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
                isBirthChanged = birth != _originalUserProfile.birth
            )
        }
        updateSaveButtonState()
    }
}
