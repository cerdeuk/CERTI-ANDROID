package org.sopt.certi.data.mapper.todomain.cert

import org.sopt.certi.data.remote.dto.response.GetPreCertDayResponseDto
import org.sopt.certi.data.remote.dto.response.GetPreCertMonthResponseDto
import org.sopt.certi.data.remote.dto.response.PreCertListResponseDto
import org.sopt.certi.data.remote.dto.response.PreCertMonthDayItem
import org.sopt.certi.domain.model.certification.CertificationData
import org.sopt.certi.domain.model.certification.PreCertDayData

fun PreCertListResponseDto.toDomain(): List<CertificationData> =
    data.map { cert ->
        CertificationData(
            certificationId = cert.certificationId,
            certificationName = cert.certificationName,
            averagePeriod = cert.averagePeriod,
            nearestTestDate = cert.nearestTestDate,
            agencyName = cert.agencyName,
            iconIndex = cert.iconIndex
        )
    }

fun GetPreCertMonthResponseDto.toDomain(): List<Int> =
    days.map { day ->
        day.toDomain()
    }


fun PreCertMonthDayItem.toDomain(): Int = this.day

fun GetPreCertDayResponseDto.toDomain(): PreCertDayData {
    return PreCertDayData(
        date = date,
        certifications = certifications.map {
            it.toDomain()
        }
    )
}