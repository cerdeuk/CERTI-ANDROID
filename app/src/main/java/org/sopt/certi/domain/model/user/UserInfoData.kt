package org.sopt.certi.domain.model.user

data class UserInfoData(
    val name: String,
    val nickname: String = "",
    val university: String = "",
    val major: String = "",
    val category: List<String> = emptyList<String>(),
    val track: String = "",
    val birthday: String = "",
    val profileImageUrl: String? = null
)
