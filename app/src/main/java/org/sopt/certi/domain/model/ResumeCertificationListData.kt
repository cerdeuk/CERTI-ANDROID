package org.sopt.certi.domain.model

data class ResumeCertificationListData(
    val name: String,
    val year: Int,
    val month: Int,
    val day: Int,
    val cardImageUrl: String,
    val tags: List<String>
)
