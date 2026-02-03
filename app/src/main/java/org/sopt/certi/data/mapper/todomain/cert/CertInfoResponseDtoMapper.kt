package org.sopt.certi.data.mapper.todomain.cert

import org.sopt.certi.data.remote.dto.response.GetCertInfoResponseDto
import org.sopt.certi.domain.model.certification.CertificationData
import org.sopt.certi.domain.type.CertStateType

fun GetCertInfoResponseDto.toDomain() = CertificationData(
    certificationId = certificationId,
    certificationName = certificationName,
    tags = tags,
    averagePeriod = averagePeriod,
    charge = charge,
    agencyName = agencyName,
    testType = testType,
    description = description,
    applicationMethod = applicationMethod,
    applicationUrl = applicationUrl,
    expirationPeriod = expirationPeriod,
    testDateInformation = testDateInformation,
    certState = CertStateType.valueOf(certState)
)
