package org.sopt.certi.data.remote.datasource

import org.sopt.certi.data.remote.dto.base.NullableApiResponse

interface PreCertRemoteDataSource {
    suspend fun acquireExpectCert(certificationId: Long, city: String? = null, state: String? = null, testDate: String? = null): NullableApiResponse<Boolean>
}
