package org.sopt.certi.data.mapper.todomain.career

import org.sopt.certi.data.remote.dto.response.GetCareersResponseDto
import org.sopt.certi.domain.model.ActivityData

fun GetCareersResponseDto.toDomain(): List<ActivityData> {
    return careerDetailResponseList.map {
        ActivityData(
            activityId = it.careerId,
            startAt = it.startAt,
            endAt = it.endAt,
            organization = it.place,
            role = it.name,
            description = it.description
        )
    }
}
