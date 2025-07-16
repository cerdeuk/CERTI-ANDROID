package org.sopt.certi.data.remote.datasource

import org.sopt.certi.data.remote.dto.base.NullableApiResponse

interface AcquisitionRemoteDataSource {
    suspend fun acquiredCert(certificationId: Long): NullableApiResponse<Boolean>
}
