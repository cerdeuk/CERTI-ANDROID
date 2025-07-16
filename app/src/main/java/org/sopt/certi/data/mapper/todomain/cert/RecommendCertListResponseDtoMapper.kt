package org.sopt.certi.data.mapper.todomain.cert

import org.sopt.certi.data.remote.dto.response.GetRecommendCertResponseDto
import org.sopt.certi.domain.model.certification.CertificationData
import org.sopt.certi.domain.model.certification.CertificationListData

fun GetRecommendCertResponseDto.toDomain() = CertificationListData(
    certificationList = recommendationList.map {
        CertificationData(
            certificationId = it.certificationId,
            certificationName = it.certificationName,
            certificationType = it.certificationType,
            testType = it.testType,
            tags = it.tags,
            recommendScore = it.recommendationScore,
            isFavorite = it.isFavorite
        )
    }
)
