package org.sopt.certi.data.mapper.todomain

import org.sopt.certi.data.remote.dto.response.SignUpResponseDto
import org.sopt.certi.domain.model.JwtResponse
import org.sopt.certi.domain.model.SignUpResult

fun SignUpResponseDto.toDomain(): SignUpResult =
    SignUpResult(
        userId = userId,
        nickName = nickName,
        university = university,
        trackType = trackType,
        major = major,
        jobs = jobs,
        jwtResponse = JwtResponse(jwtResponse.accessToken, jwtResponse.refreshToken)
    )
