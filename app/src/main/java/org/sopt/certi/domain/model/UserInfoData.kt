package org.sopt.certi.domain.model

data class UserInfoData(
    val name: String,
    val university: String,
    val major: String,
    val percentage: Int = 0,
    val category: List<String> = emptyList<String>(),
    val track: String = ""
)
