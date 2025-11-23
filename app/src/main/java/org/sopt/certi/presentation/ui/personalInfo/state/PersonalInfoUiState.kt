package org.sopt.certi.presentation.ui.personalInfo.state

import android.net.Uri

data class PersonalInfoUiState(
    val nickname: String = "",
    val name: String = "",
    val email: String = "",
    val birth: String = "",
    val profileUri: Uri? = null,
    val isNicknameChanged: Boolean = false,
    val isBirthChanged: Boolean = false,
    val isBirthValid: Boolean = true,
    val isSaveButtonEnabled: Boolean = false
)
