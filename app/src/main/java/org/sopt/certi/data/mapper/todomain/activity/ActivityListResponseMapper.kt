package org.sopt.certi.data.mapper.todomain.activity

import org.sopt.certi.data.remote.dto.response.GetActivityListResponseDto
import org.sopt.certi.domain.model.ActivityData

fun GetActivityListResponseDto.toDomain(): List<ActivityData> {
    return activityDetailResponses.map {
        ActivityData(
            activityId = it.activityId,
            startAt = it.startAt,
            endAt = it.endAt,
            organization = it.place,
            role = it.name,
            description = it.description
        )
    }
}
