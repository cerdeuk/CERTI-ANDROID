package org.sopt.certi.domain.model

data class CertificationListData(
    val certificationId: Long,
    val isLiked: Boolean,
    val certificationName: String,
    val categories: List<String>,
    val agency: String,
    val applicationMethod: String
)