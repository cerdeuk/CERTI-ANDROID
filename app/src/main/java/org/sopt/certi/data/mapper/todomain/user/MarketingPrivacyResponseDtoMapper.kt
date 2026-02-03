package org.sopt.certi.data.mapper.todomain.user

import org.sopt.certi.data.remote.dto.response.MarketingPrivacyResponseDto
import org.sopt.certi.domain.model.user.MarketingPrivacyData

fun MarketingPrivacyResponseDto.toDomain(): MarketingPrivacyData = MarketingPrivacyData(
    isAdvertisingAgreement = isAdAgreed,
    isPrivacyAgreement = isPvAgreed
)
