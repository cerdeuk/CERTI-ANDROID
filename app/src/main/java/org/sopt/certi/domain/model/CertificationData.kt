package org.sopt.certi.domain.model

import java.time.LocalDate

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
    val charge: Int = 0,
    val applicationUrl: String = "",
    val description: String = "",
    val applicationMethod: String = "",
    val cardFrontImageUrl: String = "",
    val cardBackImageUrl: String = "",
    val createdAt: LocalDate = LocalDate.now()
)
