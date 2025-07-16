package org.sopt.certi.data.remote.service

import org.sopt.certi.data.remote.dto.base.ApiResponse
import org.sopt.certi.data.remote.dto.response.GetCertInfoResponseDto
import org.sopt.certi.data.remote.dto.response.GetRecommendCertResponseDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CertService {
    @GET("api/v1/certification/recommend")
    suspend fun getRecommendCertList(): ApiResponse<GetRecommendCertResponseDto>

    @GET("api/v1/certification/{certificationId}")
    suspend fun getCertInfo(@Path("certificationId") certificationId: Long): ApiResponse<GetCertInfoResponseDto>
}
