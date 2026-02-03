package org.sopt.certi.domain.usecase.user

import org.sopt.certi.domain.repository.UserRepository
import javax.inject.Inject

class PutMajorUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(major: String): Result<Unit> =
        userRepository.putMajor(major)
}
