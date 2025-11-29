package org.sopt.certi.presentation.ui.personalInfo.state

import android.net.Uri
import org.sopt.certi.domain.model.DateData

data class PersonalInfoUiState(
    val nickname: String = "",
    val name: String = "",
    val email: String = "",
    val birth: DateData = DateData(),
    val profileUri: Uri? = null,
    val isNicknameChanged: Boolean = false,
    val isBirthChanged: Boolean = false,
    val isSaveButtonEnabled: Boolean = false
)
