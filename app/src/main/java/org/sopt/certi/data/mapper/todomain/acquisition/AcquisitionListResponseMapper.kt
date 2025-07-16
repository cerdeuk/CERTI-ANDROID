package org.sopt.certi.data.mapper.todomain.acquisition

import org.sopt.certi.data.remote.dto.response.GetAcquisitionListResponseDto
import org.sopt.certi.domain.model.certification.CertificationData
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun GetAcquisitionListResponseDto.toDomain(): List<CertificationData> {
    return getAcquisitionResponses.map {
        val createdAt = try {
            LocalDate.parse(it.createdAt, DateTimeFormatter.ISO_LOCAL_DATE)
        } catch (e: Exception) {
            e.printStackTrace()
            LocalDate.MIN
        }

        CertificationData(
            certificationId = it.acquisitionId,
            index = it.index,
            certificationName = it.name,
            createdAt = createdAt,
            cardFrontImageUrl = it.cardFrontImageUrl,
            cardBackImageUrl = it.cardBackImageUrl,
            tags = it.tags
        )
    }
}
