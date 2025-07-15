package org.sopt.certi.data.mapper.todomain

import kotlinx.serialization.SerialName
import org.sopt.certi.data.remote.dto.response.PreCertificationResponseDto
import org.sopt.certi.domain.model.CertificationData

fun PreCertificationResponseDto.toDomain() = CertificationData(
    certificationId = certificationId,
    certificationName = certificationName,
    averagePeriod = averagePeriod,
    nearestTestDate = nearestTestDate,
    agencyName = agencyName,
    iconIndex = iconIndex
)
