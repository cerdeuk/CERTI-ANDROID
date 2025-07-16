package org.sopt.certi.data.mapper.todomain.career

import org.sopt.certi.data.remote.dto.response.GetCareersResponseDto
import org.sopt.certi.domain.model.ActivityData
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun GetCareersResponseDto.toDomain(): List<ActivityData> {
    return careerDetailResponseList.map {
        fun parseDate(dateString: String): LocalDate =
            runCatching { LocalDate.parse(dateString, DateTimeFormatter.ISO_LOCAL_DATE) }
                .getOrElse {
                    it.printStackTrace()
                    LocalDate.MIN
                }

        ActivityData(
            activityId = it.careerId,
            startAt = parseDate(it.startAt),
            endAt = parseDate(it.endAt),
            organization = it.place,
            role = it.name,
            description = it.description
        )
    }
}
