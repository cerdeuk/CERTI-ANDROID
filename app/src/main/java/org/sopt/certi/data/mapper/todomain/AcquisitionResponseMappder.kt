package org.sopt.certi.data.mapper.todomain

import org.sopt.certi.data.remote.dto.response.AcquisitionResponseDto
import org.sopt.certi.domain.model.certification.CertificationData

fun AcquisitionResponseDto.toDomain() = CertificationData(
    certificationId = acquisitionId,
    index = index,
    certificationName = name,
    createdAt = createdAt,
    cardFrontImageUrl = cardFrontImageUrl,
    cardBackImageUrl = cardBackImageUrl,
    tags = tags
)
