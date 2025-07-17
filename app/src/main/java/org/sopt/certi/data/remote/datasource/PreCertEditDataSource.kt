package org.sopt.certi.data.remote.datasource

import org.sopt.certi.data.remote.dto.base.ApiResponse
import org.sopt.certi.data.remote.dto.base.NullableApiResponse

interface PreCertEditRemoteDataSource {
    suspend fun deletePreCertification(certificationId: Long): NullableApiResponse<Unit>
}
