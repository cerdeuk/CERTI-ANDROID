package org.sopt.certi.domain.model

import java.time.LocalDate

data class ActivityData(
    val activityId: Long,
    val startAt: LocalDate,
    val endAt: LocalDate,
    val organization: String,
    val role: String,
    val description: String
)
