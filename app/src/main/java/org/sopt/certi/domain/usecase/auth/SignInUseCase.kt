package org.sopt.certi.domain.usecase.auth

import org.sopt.certi.domain.model.user.UserAuth
import org.sopt.certi.domain.repository.AuthRepository

class SignInUseCase(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(accessToken: String, socialType: String): Result<UserAuth> =
        authRepository.signIn(accessToken = accessToken, socialType = socialType)
}
