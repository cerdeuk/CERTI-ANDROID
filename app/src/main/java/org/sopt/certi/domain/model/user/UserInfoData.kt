package org.sopt.certi.domain.model.user

data class UserInfoData(
    val name: String,
    val university: String,
    val major: String,
    val percentage: Int = 0,
    val category: List<String> = emptyList<String>(),
    val track: String = ""
)