package org.sopt.certi.data.mapper.todomain.user

import org.sopt.certi.data.remote.dto.response.GetInterestJobListResponseDto
import org.sopt.certi.domain.model.user.InterestedJobListData

fun GetInterestJobListResponseDto.toDomain() = InterestedJobListData(
    jobList = jobList
)
