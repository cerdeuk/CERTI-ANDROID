package org.sopt.certi.data.mapper.todomain.cert

import org.sopt.certi.data.remote.dto.response.SearchCertListResponseDto
import org.sopt.certi.domain.model.certification.CertificationData

fun SearchCertListResponseDto.toDomain(): List<CertificationData> =
    certificationSimpleList.mapIndexed { index, cert ->
        CertificationData(
            certificationId = cert.certificationId,
            certificationName = cert.certificationName,
            certificationType = cert.certificationType,
            testType = cert.testType,
            tags = cert.tags,
            isFavorite = cert.isFavorite
        )
    }
