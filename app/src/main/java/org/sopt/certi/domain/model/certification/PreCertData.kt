package org.sopt.certi.domain.model.certification

data class PreCertDayData(
    val date: String = "",
    val items: List<PreCertDayItem> = emptyList()
)

data class PreCertDayItem(
    val userPreCertificationId: Int = 0,
    val certificationName: String = "",
    val certificationType: String = "",
    val description: String = "",
    val location: String = "",
    val time: String = "",
)