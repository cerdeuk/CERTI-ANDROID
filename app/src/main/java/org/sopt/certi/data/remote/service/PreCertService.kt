package org.sopt.certi.data.remote.service

import org.sopt.certi.data.remote.dto.base.NullableApiResponse
import retrofit2.http.POST
import retrofit2.http.Path

interface PreCertService {
    @POST("api/v1/home/pre-certification/{certificationId}")
    suspend fun acquireExpectCert(@Path("certificationId") certificationId: Long): NullableApiResponse<Boolean>
}