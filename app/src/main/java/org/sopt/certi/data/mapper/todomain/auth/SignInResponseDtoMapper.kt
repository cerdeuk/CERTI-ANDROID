package org.sopt.certi.data.mapper.todomain.auth

import org.sopt.certi.data.remote.dto.response.SignInResponseDto
import org.sopt.certi.domain.model.user.JwtResponse
import org.sopt.certi.domain.model.user.UserInformationAuth
import org.sopt.certi.domain.model.user.UserAuth

fun SignInResponseDto.toDomain() = UserAuth(
    needSignUp = needSignUp,
    preSignupToken = preSignupToken.toString(),
    userInformation = UserInformationAuth(
        socialId = userInformation?.socialId ?: "",
        socialType = userInformation?.socialType ?: "",
        email = userInformation?.email ?: "",
        name = userInformation?.name ?: "",
        profileImageUrl = userInformation?.profileImageUrl ?: ""
    ),
    jwtResponse = tokenResponse?.let {
        JwtResponse(
            accessToken = it.accessToken,
            refreshToken = it.refreshToken
        )
    }
)
