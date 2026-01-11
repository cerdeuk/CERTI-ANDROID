package org.sopt.certi.data.remote.service

import org.sopt.certi.data.remote.dto.base.ApiResponse
import org.sopt.certi.data.remote.dto.response.CertListResponseDto
import org.sopt.certi.data.remote.dto.response.GetCertInfoResponseDto
import org.sopt.certi.data.remote.dto.response.GetRecommendCertResponseDto
import org.sopt.certi.data.remote.dto.response.Top3CertListResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CertService {
    @GET("api/v1/certification/recommend")
    suspend fun getRecommendCertList(): ApiResponse<GetRecommendCertResponseDto>

    @GET("api/v1/certification/{certificationId}")
    suspend fun getCertInfo(@Path("certificationId") certificationId: Long): ApiResponse<GetCertInfoResponseDto>

    @GET("/api/v1/certification/search")
    suspend fun searchCertList(
        @Query("keyword") keyword: String
    ): ApiResponse<CertListResponseDto>

    @GET("/api/v1/certification/jobs")
    suspend fun getJobCertList(
        @Query("isFavorite") isFavorite: Boolean,
        @Query("jobs") jobs: String
    ): ApiResponse<CertListResponseDto>

    @GET("/api/v1/certification/tracks")
    suspend fun getTrackCertList(
        @Query("isFavorite") isFavorite: Boolean,
        @Query("tracks") tracks: String
    ): ApiResponse<CertListResponseDto>

    @GET("/api/v1/certification/track")
    suspend fun getTop3TrackCertList(): ApiResponse<List<Top3CertListResponseDto>>

    @GET("/api/v1/certification/job")
    suspend fun getTop3JobCertList(): ApiResponse<List<Top3CertListResponseDto>>
}
