package org.sopt.certi.domain.model

import java.time.LocalDate

data class ResumeData(
    val certificationId: Long,
    val certificationName: String,
    val averagePeriod: String = "",
    val nearestTestDate: String = "",
    val agencyName: String,
    val iconIndex: Int = 0,
    val testType: String,
    val isFavorite: Boolean = false,
    val charge: Int = 0,
    val applicationUrl: String = "",
    val description: String = "",
    val applicationMethod: String = "",
    val cardFrontImageUrl: String = "",
    val cardBackImageUrl: String = "",
    val createdAt: LocalDate = LocalDate.now()
)
