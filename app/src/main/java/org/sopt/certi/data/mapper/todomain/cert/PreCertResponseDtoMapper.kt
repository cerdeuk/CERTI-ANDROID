package org.sopt.certi.data.mapper.todomain.cert

import org.sopt.certi.core.util.splitDateTime
import org.sopt.certi.data.remote.dto.response.GetPreCertDayListResponseDto
import org.sopt.certi.data.remote.dto.response.GetPreCertMonthResponseDto
import org.sopt.certi.data.remote.dto.response.PreCertDayItemResponseDto
import org.sopt.certi.data.remote.dto.response.PreCertListResponseDto
import org.sopt.certi.data.remote.dto.response.PreCertMonthDayItem
import org.sopt.certi.domain.model.certification.CertificationData
import org.sopt.certi.domain.model.certification.PreCertDayData

fun PreCertListResponseDto.toDomain(): List<CertificationData> =
    data.map { cert ->
        val (date, time) = cert.testDate.splitDateTime()
        CertificationData(
            certificationId = cert.certificationId,
            preCertificationId = cert.preCertificationId,
            certificationName = cert.certificationName,
            certificationType = cert.certificationType,
            description = cert.description,
            averagePeriod = cert.averagePeriod,
            nearestTestDate = cert.nearestTestDate,
            agencyName = cert.agencyName,
            iconIndex = cert.iconIndex,
            city = cert.city,
            state = cert.state,
            testDate = date,
            testTime = time,
            isAcquired = false
        )
    }

fun GetPreCertMonthResponseDto.toDomain(): List<Int> =
    days.map { day ->
        day.toDomain()
    }

fun PreCertMonthDayItem.toDomain(): Int = this.day

fun GetPreCertDayListResponseDto.toDomain(): PreCertDayData {
    return PreCertDayData(
        date = date,
        certifications = certifications?.map {
            it.toDomain()
        }
    )
}

fun PreCertDayItemResponseDto.toDomain(): CertificationData {
    return CertificationData(
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
        city = city,
        state = state,
        testDate = testDate,
        testTime = testDate.splitDateTime().second,
        isAcquired = isAcquired
    )
}
