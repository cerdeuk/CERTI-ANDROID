package org.sopt.certi.domain.usecase

import org.sopt.certi.domain.model.UserAuth
import org.sopt.certi.domain.repository.AuthRepository

class SignInUseCase(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(accessToken: String, socialType: String): Result<UserAuth> =
        authRepository.signIn(accessToken = accessToken, socialType = socialType)
}
