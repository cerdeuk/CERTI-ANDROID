package org.sopt.certi.data.mapper.todomain.cert

import org.sopt.certi.data.remote.dto.response.PreCertListResponseDto
import org.sopt.certi.domain.model.certification.CertificationData


fun PreCertListResponseDto.toDomain(): List<CertificationData> =
    preCertificationList.map { cert ->
        CertificationData(
            certificationId = cert.certificationId,
            certificationName = cert.certificationName,
            averagePeriod = cert.averagePeriod,
            nearestTestDate = cert.nearestTestDate,
            agencyName = cert.agencyName,
            iconIndex = cert.iconIndex
        )
    }
