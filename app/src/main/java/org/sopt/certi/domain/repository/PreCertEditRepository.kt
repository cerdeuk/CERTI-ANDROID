package org.sopt.certi.domain.repository

interface PreCertEditRepository {
    suspend fun deletePreCertification(certificationId: Long): Result<Unit>
}
