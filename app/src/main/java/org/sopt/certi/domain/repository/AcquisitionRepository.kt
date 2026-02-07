package org.sopt.certi.domain.repository

import org.sopt.certi.domain.model.certification.CertificationData

interface AcquisitionRepository {
    suspend fun acquiredCert(certificationId: Long): Result<Boolean>
    suspend fun getAcquisitionList(): Result<List<CertificationData>>
    suspend fun getAcquisitionDetail(acquisitionId: Long): Result<CertificationData>
    suspend fun deleteAcquisition(acquisitionId: Long): Result<Unit>
    suspend fun updateAcquisition(acquisitionId: Long, acquisitionDate: String, grade: String): Result<Unit>
}
