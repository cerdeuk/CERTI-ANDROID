package org.sopt.certi.domain.model.user

data class UserInfoData(
    val userId: Long = 0,
    val name: String,
    val nickname: String = "",
    val university: String = "",
    val major: String = "",
    val category: List<String> = emptyList<String>(),
    val job: String = "",
    val track: String = "",
    val birthday: String? = null,
    val profileImageUrl: String? = null
) {
    companion object {
        fun defaultData() = UserInfoData(
            userId = 0,
            name = "",
            nickname = "",
            university = "",
            major = "",
            category = emptyList(),
            job = "",
            track = "",
            birthday = null,
            profileImageUrl = null
        )
    }
}
