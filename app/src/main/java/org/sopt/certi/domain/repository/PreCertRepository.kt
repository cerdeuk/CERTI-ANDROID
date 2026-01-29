package org.sopt.certi.domain.repository

interface PreCertRepository {
    suspend fun acquireExpectCert(certificationId: Long, city: String, state: String, testDate: String): Result<Boolean>
}
