package org.sopt.certi.domain.usecase.user

import org.sopt.certi.domain.model.user.PersonalInfo
import org.sopt.certi.domain.repository.UserRepository
import javax.inject.Inject

class PutPersonalInfoUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(request: PersonalInfo): Result<Unit> = userRepository.putPersonalInfo(request)
}
