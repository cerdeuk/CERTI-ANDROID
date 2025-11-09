package org.sopt.certi.presentation.ui.my.state

import android.net.Uri

data class PersonalInfoUiState(
    val nickname: String,
    val name: String,
    val email: String,
    val birth: String,
    val profileUri: Uri?,
    val isSaveButtonEnabled: Boolean
)
