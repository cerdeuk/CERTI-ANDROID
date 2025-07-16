package org.sopt.certi.data.remote.service

import org.sopt.certi.data.remote.dto.base.ApiResponse
import org.sopt.certi.data.remote.dto.response.GetRecommendCertResponseDto
import org.sopt.certi.data.remote.dto.response.CertListResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface CertService {
    @GET("api/v1/certification/recommend")
    suspend fun getRecommendCertList(): ApiResponse<GetRecommendCertResponseDto>

    @GET("/api/v1/certification/search")
    suspend fun searchCertList(
        @Query("keyword") keyword: String
    ): ApiResponse<CertListResponseDto>

    @GET("/api/v1/certification")
    suspend fun getCategoryCertList(
        @Query("isFavorite") isFavorite: Boolean,
        @Query("jobs") jobs: List<String>
    ): ApiResponse<CertListResponseDto>
}
