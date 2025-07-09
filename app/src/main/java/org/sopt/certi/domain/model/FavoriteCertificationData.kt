package org.sopt.certi.domain.model

data class FavoriteCertificationData(
    val certificationId: Int,
    val certificationName: String,
    val testType: String,
    val agencyName: String,
    val qualificationType: String
)
