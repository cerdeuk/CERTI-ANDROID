package org.sopt.certi.presentation.ui.my.state

import android.net.Uri

data class PersonalInfoUiState(
    var nickname: String,
    var name: String,
    var email: String,
    var birth: String,
    var profileUri: Uri?
)
