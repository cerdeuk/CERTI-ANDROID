package org.sopt.certi.presentation.ui.my

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.sopt.certi.presentation.type.NickNameValidType
import org.sopt.certi.presentation.ui.my.state.PersonalInfoUiState
import javax.inject.Inject

class PersonalInfoViewModel @Inject constructor() : ViewModel() {
    private val defaultState = PersonalInfoUiState("", "", "", "", null, false)

    private val _uiState = MutableStateFlow(defaultState)
    val uiState = _uiState.asStateFlow()

    private val _nickNameValidTypeUiState = MutableStateFlow(NickNameValidType.DEFAULT)
    val nickNameValidTypeUiState = _nickNameValidTypeUiState.asStateFlow()

    private var _initialUiState = MutableStateFlow(defaultState)
    val initialUiState = _initialUiState.asStateFlow()

    init {
        loadPersonalInfoData()
    }

    private fun loadPersonalInfoData() {
        viewModelScope.launch {
            val loadedState = PersonalInfoUiState(
                nickname = "nick",
                name = "name",
                email = "certification@gmail.com",
                birth = "",
                profileUri = null,
                isSaveButtonEnabled = false
            )

            _initialUiState.update { loadedState.copy() }
            _uiState.update { loadedState }
        }
    }

    private fun updateSaveButtonState() {
        val initial = _initialUiState.value
        val currentStateForComparison = _uiState.value.copy(
            isSaveButtonEnabled = initial.isSaveButtonEnabled
        )
        val isContentChanged = currentStateForComparison != initial
        val isNicknameValid = _nickNameValidTypeUiState.value in setOf(NickNameValidType.VALID, NickNameValidType.DEFAULT)

        _uiState.update {
            it.copy(isSaveButtonEnabled = isContentChanged && isNicknameValid)
        }
    }

    fun onSaveClick() {
        viewModelScope.launch {
            _nickNameValidTypeUiState.value = NickNameValidType.DEFAULT
            val savedState = _uiState.value.copy(isSaveButtonEnabled = false)
            _uiState.value = savedState
            _initialUiState.value = savedState
        }
    }

    fun onProfileUriChange(uri: Uri?) {
        _uiState.update { it.copy(profileUri = uri) }
        updateSaveButtonState()
    }

    fun onNickNameChange(nickname: String) {
        _uiState.update { it.copy(nickname = nickname) }
        _nickNameValidTypeUiState.update {
            when {
                nickname.isEmpty() -> NickNameValidType.EMPTY
                nickname.contains("시발") -> NickNameValidType.INVALID
                nickname == _initialUiState.value.nickname -> NickNameValidType.DEFAULT
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

    fun onBirthChange(birth: String) {
        _uiState.update {
            it.copy(birth = birth)
        }
        updateSaveButtonState()
    }
}
