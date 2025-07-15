package org.sopt.certi.data.mapper.todomain

import kotlinx.serialization.SerialName
import org.sopt.certi.data.remote.dto.response.FavoriteResponseDto
import org.sopt.certi.data.remote.dto.response.PreCertificationResponseDto
import org.sopt.certi.domain.model.CertificationData

fun FavoriteResponseDto.toDomain() = CertificationData(
    certificationId = certificationId,
    isFavorite = isFavorite,
    certificationName = certificationName,
    testType = testType,
    agencyName = agencyName
)