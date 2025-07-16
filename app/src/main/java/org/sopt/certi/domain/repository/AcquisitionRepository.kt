package org.sopt.certi.domain.repository

interface AcquisitionRepository {
    suspend fun acquiredCert(certificationId: Long): Result<Boolean>
}
