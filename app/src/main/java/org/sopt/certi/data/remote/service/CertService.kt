package org.sopt.certi.data.remote.service

import org.sopt.certi.data.remote.dto.base.ApiResponse
import org.sopt.certi.data.remote.dto.response.GetRecommendCertResponseDto
import retrofit2.http.GET

interface CertService {
    @GET("api/v1/certification/recommend")
    suspend fun getRecommendCertList(): ApiResponse<GetRecommendCertResponseDto>
}
