package org.sopt.certi.data.mapper.todomain.acquisition

import org.sopt.certi.core.util.toLocalDateOrMin
import org.sopt.certi.data.remote.dto.response.GetAcquisitionListResponseDto
import org.sopt.certi.domain.model.certification.CertificationData

fun GetAcquisitionListResponseDto.toDomain(): List<CertificationData> {
    return getAcquisitionResponses.map {
        CertificationData(
            certificationId = it.acquisitionId,
            index = it.index,
            certificationName = it.name,
            createdAt = it.createdAt.toLocalDateOrMin(),
            cardFrontImageUrl = it.cardFrontImageUrl,
            cardBackImageUrl = it.cardBackImageUrl,
            tags = it.tags
        )
    }
}
