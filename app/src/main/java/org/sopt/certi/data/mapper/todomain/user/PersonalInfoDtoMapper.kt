package org.sopt.certi.data.mapper.todomain.user

import org.sopt.certi.core.util.toDateData
import org.sopt.certi.data.remote.dto.response.GetPersonalInfoResponseDto
import org.sopt.certi.domain.model.DateData
import org.sopt.certi.domain.model.user.PersonalInfo

fun GetPersonalInfoResponseDto.toDomain(): PersonalInfo = PersonalInfo(
    name = name,
    nickname = nickName,
    email = email,
    birth = birthDate?.toDateData() ?: DateData(),
    profileImageUrl = profileImageURL ?: ""
)
