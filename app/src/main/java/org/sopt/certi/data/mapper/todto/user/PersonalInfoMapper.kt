package org.sopt.certi.data.mapper.todto.user

import org.sopt.certi.data.remote.dto.request.PutPersonalInfoRequestDto
import org.sopt.certi.domain.model.user.PersonalInfo

fun PersonalInfo.toDto(): PutPersonalInfoRequestDto = PutPersonalInfoRequestDto(
    name = name,
    email = email,
    nickName = nickname,
    birthDate = if (!birth.isComplete) "" else "${birth.year}.${birth.month?.let { "%02d".format(it) }}.${birth.day?.let { "%02d".format(it) }}",
    publicURL = profileImageUrl
)
