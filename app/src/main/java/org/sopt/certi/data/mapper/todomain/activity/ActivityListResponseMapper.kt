package org.sopt.certi.data.mapper.todomain.activity

import org.sopt.certi.data.remote.dto.response.GetActivityListResponseDto
import org.sopt.certi.domain.model.ActivityData
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun GetActivityListResponseDto.toDomain(): List<ActivityData> {
    return activityDetailResponses.map {
        fun parseDate(dateString: String): LocalDate =
            runCatching { LocalDate.parse(dateString, DateTimeFormatter.ISO_LOCAL_DATE) }
                .getOrElse {
                    it.printStackTrace()
                    LocalDate.MIN
                }

        ActivityData(
            activityId = it.activityId,
            startAt = parseDate(it.startAt),
            endAt = parseDate(it.endAt),
            organization = it.place,
            role = it.name,
            description = it.description
        )
    }
}
