package org.sopt.certi.domain.usecase.user

import org.sopt.certi.domain.repository.UserRepository
import javax.inject.Inject

class CheckNicknameValidationUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(keyword: String): Result<Unit> =
        userRepository.checkNicknameValidation(keyword)
}
