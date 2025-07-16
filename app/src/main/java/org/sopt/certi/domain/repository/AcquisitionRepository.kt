package org.sopt.certi.domain.repository

import org.sopt.certi.domain.model.CertificationData

interface AcquisitionRepository {
    suspend fun getAcquisitionList(): Result<List<CertificationData>>
}
