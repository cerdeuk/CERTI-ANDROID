package org.sopt.certi.data.remote.service

import org.sopt.certi.data.remote.dto.base.NullableApiResponse
import retrofit2.http.POST
import retrofit2.http.Path

interface AcquisitionService {
    @POST("api/v1/acquisition/{certificationId}")
    suspend fun acquiredCert(@Path("certificationId") certificationId: Long): NullableApiResponse<Boolean>
}
