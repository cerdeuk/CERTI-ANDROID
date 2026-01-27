package org.sopt.certi.domain.model.certification

data class PreCertDayData(
    val date: String = "",
    val certifications: List<CertificationData> = emptyList()
)