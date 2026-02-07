package org.sopt.certi.data.mapper.todomain.user

import org.sopt.certi.data.remote.dto.response.UserInfoResponseDto
import org.sopt.certi.domain.model.user.UserInfoData

fun UserInfoResponseDto.toDomain() = UserInfoData(
    userId = userId,
    nickname = nickname,
    name = name,
    university = university,
    major = major,
    job = job,
    profileImageUrl = profileImage,
    birthday = birthDate
)
