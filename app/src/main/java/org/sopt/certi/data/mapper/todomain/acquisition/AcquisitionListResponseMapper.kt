package org.sopt.certi.data.mapper.todomain.acquisition

import org.sopt.certi.data.remote.dto.response.GetAcquisitionListResponseDto
import org.sopt.certi.domain.model.certification.CertificationData

fun GetAcquisitionListResponseDto.toDomain(): List<CertificationData> {
    return acquisitionListDetailResponses.map {
        CertificationData(
            acquisitionId = it.acquisitionId,
            certificationId = it.certificationId,
            certificationType = it.certificationType,
            index = it.index,
            certificationName = it.name,
            description = it.description,
            cardFrontImageUrl = it.cardFrontImageUrl,
            tags = it.tags,
            acquisitionDate = it.acquisitionDate,
            grade = it.grade ?: "",
            isAcquired = true
        )
    }
}
