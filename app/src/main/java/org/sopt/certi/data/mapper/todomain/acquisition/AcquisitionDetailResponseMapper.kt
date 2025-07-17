package org.sopt.certi.data.mapper.todomain.acquisition

import org.sopt.certi.core.util.toLocalDateOrMin
import org.sopt.certi.data.remote.dto.response.GetAcquisitionDetailResponseDto
import org.sopt.certi.domain.model.certification.CertificationData

fun GetAcquisitionDetailResponseDto.toDomain(): CertificationData {
    return CertificationData(
        certificationId = acquisitionId,
        certificationName = name,
        index = index,
        cardFrontImageUrl = cardFrontImageUrl,
        cardBackImageUrl = cardBackImageUrl,
        tags = tags,
        description = description,
        createdAt = createdAt.toLocalDateOrMin()
    )
}
