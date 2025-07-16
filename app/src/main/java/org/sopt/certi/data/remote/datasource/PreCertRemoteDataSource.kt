package org.sopt.certi.data.remote.datasource

import org.sopt.certi.data.remote.dto.base.NullableApiResponse

interface PreCertRemoteDataSource {
    suspend fun acquireExpectCert(certificationId: Long): NullableApiResponse<Boolean>
}
