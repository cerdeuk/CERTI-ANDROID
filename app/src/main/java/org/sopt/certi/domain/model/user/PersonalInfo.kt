package org.sopt.certi.domain.model.user

import org.sopt.certi.domain.model.DateData

data class PersonalInfo(
    val name: String,
    val nickname: String,
    val email: String,
    val birth: DateData,
    val profileImageUrl: String
)
