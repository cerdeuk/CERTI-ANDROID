package org.sopt.certi.domain.repository

import org.sopt.certi.domain.model.certification.CertificationListData

interface CertRepository {
    suspend fun getRecommendCertList(): Result<CertificationListData>
}
