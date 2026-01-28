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
import org.sopt.certi.domain.usecase.image.GetPresignedUrlUseCase
import org.sopt.certi.domain.usecase.image.UploadImageToS3UseCase
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
    private val getPresignedUrlUseCase: GetPresignedUrlUseCase,
    private val uploadImageToS3UseCase: UploadImageToS3UseCase,
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
        getPersonalInfoUseCase()
            .onSuccess { result ->
                _originalPersonalInfo = result
                _uiState.update {
                    it.copy(
                        name = result.name,
                        nickname = result.nickname,
                        email = result.email,
                        birth = result.birth,
                        profileUri = if (result.profileImageUrl.isNotEmpty()) result.profileImageUrl.toUri() else null
                    )
                }
            }
            .onFailure { error ->
                Timber.e(error, "LoadPersonalInfoData Failed")
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
        try {
            val currentState = _uiState.value
            val original = _originalPersonalInfo ?: return@launch

            val finalProfileImageUrl = getFinalProfileImageUrl(currentState, original)

            val request = PersonalInfo(
                name = currentState.name,
                nickname = currentState.nickname,
                email = currentState.email,
                birth = currentState.birth,
                profileImageUrl = finalProfileImageUrl
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
                    Timber.d("정보 수정 실패")
                }
        } catch (e: Exception) {
            Timber.e(e, "정보 수정 실패")
        }
    }

    private suspend fun getFinalProfileImageUrl(
        currentState: EditPersonalInfoUiState,
        original: PersonalInfo
    ): String {
        val currentUriString = currentState.profileUri?.toString() ?: ""
        val originalUrlString = original.profileImageUrl

        val isProfileChanged = currentUriString != originalUrlString

        return if (isProfileChanged) {
            if (currentState.profileUri != null) {
                uploadImageAndGetPublicUrl(currentState.profileUri)
            } else {
                ""
            }
        } else {
            originalUrlString
        }
    }

    private suspend fun uploadImageAndGetPublicUrl(uri: Uri): String {
        val presignedInfo = getPresignedUrlUseCase().getOrThrow()
        uploadImageToS3UseCase(presignedInfo.presignedUrl, uri.toString()).getOrThrow()
        return presignedInfo.publicUrl
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
