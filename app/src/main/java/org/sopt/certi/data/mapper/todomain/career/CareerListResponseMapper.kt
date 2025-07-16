package org.sopt.certi.data.mapper.todomain.career

import org.sopt.certi.data.remote.dto.response.GetCareersResponseDto
import org.sopt.certi.domain.model.ActivityData
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun GetCareersResponseDto.toDomain(): List<ActivityData> {
    return careerDetailResponseList
        .take(4)
        .map {
            val startAt = try {
                LocalDate.parse(it.startAt, DateTimeFormatter.ISO_LOCAL_DATE)
            } catch (e: Exception) {
                e.printStackTrace()
                LocalDate.MIN
            }
            val endAt = try {
                LocalDate.parse(it.endAt, DateTimeFormatter.ISO_LOCAL_DATE)
            } catch (e: Exception) {
                e.printStackTrace()
                LocalDate.MIN
            }

            ActivityData(
                activityId = it.careerId,
                startAt = startAt,
                endAt = endAt,
                organization = it.place,
                role = it.name,
                description = it.description
            )
        }
}
