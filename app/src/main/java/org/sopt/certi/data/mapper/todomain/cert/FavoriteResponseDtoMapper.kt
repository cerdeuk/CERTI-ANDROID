package org.sopt.certi.data.mapper.todomain.cert

import org.sopt.certi.data.remote.dto.response.FavoriteListResponseDto
import org.sopt.certi.domain.model.certification.CertificationData

fun FavoriteListResponseDto.toDomain(): List<CertificationData> =
    data.map { cert ->
        CertificationData(
            certificationId = cert.certificationId,
            certificationName = cert.certificationName,
            testType = cert.testType,
            agencyName = cert.agencyName,
            isFavorite = cert.isFavorite
        )
    }

