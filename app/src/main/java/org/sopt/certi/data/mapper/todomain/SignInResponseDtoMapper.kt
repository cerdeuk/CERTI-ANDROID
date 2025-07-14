package org.sopt.certi.data.mapper.todomain

import org.sopt.certi.data.remote.dto.response.SignInResponseDto
import org.sopt.certi.domain.model.UserInformationAuth
import org.sopt.certi.domain.model.UserPreAuth

fun SignInResponseDto.toDomain() = UserPreAuth(
    needSignUp = needSignUp,
    preSignupToken = preSignupToken,
    userInformation = UserInformationAuth(
        email = userInformation.email,
        nickname = userInformation.nickname,
        profileImageUrl = userInformation.profileImageUrl
    )
)
