package org.sopt.certi.domain.repository

interface PreCertRepository {
    suspend fun acquireExpectCert(certificationId: Long): Result<Boolean>
}
