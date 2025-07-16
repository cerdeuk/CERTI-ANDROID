package org.sopt.certi.data.mapper.todomain.acquisition

import org.sopt.certi.data.remote.dto.response.GetAcquisitionDetailResponseDto
import org.sopt.certi.domain.model.certification.CertificationData
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun GetAcquisitionDetailResponseDto.toDomain(): CertificationData {
    val createdAt = try {
        LocalDate.parse(createdAt, DateTimeFormatter.ISO_LOCAL_DATE)
    } catch (e: Exception) {
        e.printStackTrace()
        LocalDate.MIN
    }

    return CertificationData(
        certificationId = acquisitionId,
        certificationName = name,
        index = index,
        cardFrontImageUrl = cardFrontImageUrl,
        cardBackImageUrl = cardBackImageUrl,
        tags = tags,
        description = description,
        createdAt = createdAt
    )
}
