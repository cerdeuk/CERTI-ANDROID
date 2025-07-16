package org.sopt.certi.domain.repository

import org.sopt.certi.domain.model.certification.CertificationData
import org.sopt.certi.domain.model.certification.CertificationListData

interface CertRepository {
    suspend fun getRecommendCertList(): Result<CertificationListData>
    suspend fun searchCertList(keyword: String): Result<List<CertificationData>>
    suspend fun getCategoryCertList(isFavorite: Boolean, jobs: String): Result<List<CertificationData>>
}
