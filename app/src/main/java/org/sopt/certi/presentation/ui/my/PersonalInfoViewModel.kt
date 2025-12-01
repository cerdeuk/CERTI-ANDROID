package org.sopt.certi.presentation.ui.my

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
    private val _uiState = MutableStateFlow(PersonalInfoUiState("", "", "", "", null))
    val uiState = _uiState.asStateFlow()

    private val _nickNameValidTypeUiState = MutableStateFlow(NickNameValidType.DEFAULT)
    val nickNameValidTypeUiState = _nickNameValidTypeUiState.asStateFlow()

    init {
        loadPersonalInfoData()
    }

    private fun loadPersonalInfoData() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    nickname = "nick",
                    name = "name",
                    email = "certification@gmail.com",
                    birth = ""
                )
            }
        }
    }

    fun onSaveClick() {}
    fun onProfileUriChange() {}
    fun onNickNameChange(nickname: String) {}
    fun onNickNameCheckButtonClick() {}
    fun onNameChange(name: String) {}
    fun onEmailChange(email: String) {}
    fun onBirthChange(birth: String) {}
}
