package org.sopt.certi.domain.repository

import org.sopt.certi.domain.model.CertificationListData

interface CertRepository {
    suspend fun getRecommendCertList(): Result<CertificationListData>
}