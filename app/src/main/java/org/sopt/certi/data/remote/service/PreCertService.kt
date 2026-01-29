package org.sopt.certi.data.remote.service

import org.sopt.certi.data.remote.dto.base.NullableApiResponse
import org.sopt.certi.data.remote.dto.request.AddPreCertificationRequestDto
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface PreCertService {
    @POST("api/v1/home/pre-certification/")
    suspend fun acquireExpectCert(@Body request: AddPreCertificationRequestDto): NullableApiResponse<Boolean>
}
