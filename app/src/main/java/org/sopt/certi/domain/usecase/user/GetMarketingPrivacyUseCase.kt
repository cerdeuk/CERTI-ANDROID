package org.sopt.certi.domain.usecase.user

import org.sopt.certi.domain.model.user.MarketingPrivacyData
import org.sopt.certi.domain.repository.UserRepository
import javax.inject.Inject

class GetMarketingPrivacyUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(): Result<MarketingPrivacyData> =
        userRepository.getMarketingPrivacyAgreement()
}
