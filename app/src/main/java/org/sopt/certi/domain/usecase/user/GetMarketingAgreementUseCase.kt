package org.sopt.certi.domain.usecase.user

import org.sopt.certi.domain.repository.UserRepository
import javax.inject.Inject

class GetMarketingAgreementUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(): Result<Boolean> =
        userRepository.getMarketingAgreement()
}
