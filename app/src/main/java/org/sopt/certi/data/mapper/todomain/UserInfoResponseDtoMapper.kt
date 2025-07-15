package org.sopt.certi.data.mapper.todomain

import org.sopt.certi.data.remote.dto.response.UserInfoResponseDto
import org.sopt.certi.domain.model.UserInfoData

fun UserInfoResponseDto.toDomain() = UserInfoData(
    name = name,
    university = university,
    major = major,
    percentage = percentage
)
