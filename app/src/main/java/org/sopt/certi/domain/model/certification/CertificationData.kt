package org.sopt.certi.domain.model.certification

import java.time.LocalDate

data class CertificationListData(
    val certificationList: List<CertificationData>
)

data class CertificationData(
    val certificationId: Long,
    val certificationName: String,
    val certificationType: String = "",
    val averagePeriod: String = "",
    val nearestTestDate: String = "",
    val recommendScore: Int = 0,
    val agencyName: String = "",
    val iconIndex: Int = 0,
    val testType: String = "",
    val tags: List<String> = listOf(),
    val isFavorite: Boolean = false,
    val charge: String = "0",
    val index: Int = 0,
    val applicationUrl: String = "",
    val description: String = "",
    val applicationMethod: String = "",
    val cardFrontImageUrl: String = "",
    val cardBackImageUrl: String = "",
    val createdAt: LocalDate = LocalDate.now(),
    val testDateInformation: String = "",
    val expirationPeriod: String = "",

    // 아직 서버에 없음 근데 필요한것들
    var isAcquired: Boolean = false, // 취득 여부
    var level: String = "", // 레벨
    var placement: String = "", // 시험장소
    var testTime: String = "" // 시험시간
)
