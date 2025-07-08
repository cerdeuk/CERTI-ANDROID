package org.sopt.certi.domain.model

data class PreCertificationData(
    val certificationId: Int,
    val certificationName: String,
    val averagePeriod: String,
    val testDate: String,
    val agencyName: String,
    val emojiIndex: Int
)
