package org.sopt.certi.data.mapper.todomain

import org.sopt.certi.data.remote.dto.response.GetInterestJobListResponseDto
import org.sopt.certi.domain.model.InterestedJobListData

fun GetInterestJobListResponseDto.toDomain() = InterestedJobListData(
    jobList = jobList
)