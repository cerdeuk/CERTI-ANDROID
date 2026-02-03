package org.sopt.certi.domain.usecase.user

import org.sopt.certi.domain.repository.UserRepository
import javax.inject.Inject

class PatchMarketingAgreementUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(agreement: Boolean): Result<Unit> =
        userRepository.patchMarketingAgreement(agreement)
}
