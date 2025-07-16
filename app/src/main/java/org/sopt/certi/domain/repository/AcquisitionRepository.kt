package org.sopt.certi.domain.repository

import org.sopt.certi.domain.model.certification.CertificationData

interface AcquisitionRepository {
    suspend fun getAcquisitionList(): Result<List<CertificationData>>
}
