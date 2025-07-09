package org.sopt.certi.domain.model

data class RecommendedCertificationData(
    val name: String,
    val score: Int,
    val categories: List<String>
)
