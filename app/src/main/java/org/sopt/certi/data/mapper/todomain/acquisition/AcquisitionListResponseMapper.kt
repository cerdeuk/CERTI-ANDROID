package org.sopt.certi.data.mapper.todomain.acquisition

import org.sopt.certi.data.remote.dto.response.GetAcquisitionListResponseDto
import org.sopt.certi.domain.model.certification.CertificationData
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun GetAcquisitionListResponseDto.toDomain(): List<CertificationData> {
    return getAcquisitionResponses
        .take(3)
        .map {
            val parsedDate = try {
                LocalDate.parse(it.createdAt, DateTimeFormatter.ISO_LOCAL_DATE)
            } catch (e: Exception) {
                e.printStackTrace()
                LocalDate.MIN
            }

            CertificationData(
                certificationId = it.acquisitionId,
                index = it.index,
                certificationName = it.name,
                createdAt = parsedDate,
                cardFrontImageUrl = it.cardFrontImageUrl,
                cardBackImageUrl = it.cardBackImageUrl,
                tags = it.tags
            )
        }
}
