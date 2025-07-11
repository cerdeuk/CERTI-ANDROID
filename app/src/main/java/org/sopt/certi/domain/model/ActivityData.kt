package org.sopt.certi.domain.model

data class ActivityData(
    val startAt: String,
    val endAt: String,
    val organization: String,
    val role: String,
    val description: String
)
