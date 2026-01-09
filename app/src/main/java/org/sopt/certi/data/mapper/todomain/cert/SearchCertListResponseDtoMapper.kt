package org.sopt.certi.data.mapper.todomain.cert

import org.sopt.certi.data.remote.dto.response.CertListResponseDto
import org.sopt.certi.domain.model.certification.CertificationData

fun CertListResponseDto.toDomain(): List<CertificationData> =
    certificationSimpleList.mapIndexed { index, cert ->
        CertificationData(
            certificationId = cert.certificationId,
            certificationName = cert.certificationName,
            certificationType = cert.certificationType,
            testType = cert.testType,
            tags = cert.tags,
            description = cert.description,
            isFavorite = cert.isFavorite
        )
    }
