package org.sopt.certi.data.mapper.todomain.cert

import org.sopt.certi.data.remote.dto.response.Top3CertListResponseDto
import org.sopt.certi.domain.model.certification.CertificationData

fun Top3CertListResponseDto.toDomain(): CertificationData =
    CertificationData(
        certificationId = 0,
        certificationName = certificationName,
        certificationType = certificationType
    )
