package org.sopt.certi.domain.model

data class CertificationListData(
    val certificationId: Long,
    var isLiked: Boolean,
    val certificationName: String,
    val categories: List<String>,
    val agency: String,
    val applicationMethod: String
)
