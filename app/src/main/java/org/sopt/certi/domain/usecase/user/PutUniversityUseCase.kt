package org.sopt.certi.domain.usecase.user

import org.sopt.certi.domain.repository.UserRepository
import javax.inject.Inject

class PutUniversityUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(university: String): Result<Unit> =
        userRepository.putUniversity(university)
}
