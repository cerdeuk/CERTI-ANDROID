package org.sopt.certi.data.remote.datasource

import org.sopt.certi.data.remote.dto.base.ApiResponse
import org.sopt.certi.data.remote.dto.response.CertListResponseDto
import org.sopt.certi.data.remote.dto.response.GetCertInfoResponseDto
import org.sopt.certi.data.remote.dto.response.GetRecommendCertResponseDto
import org.sopt.certi.data.remote.dto.response.Top3CertListResponseDto

interface CertRemoteDataSource {
    suspend fun getRecommendCertList(): ApiResponse<GetRecommendCertResponseDto>
    suspend fun searchCertList(keyword: String): ApiResponse<CertListResponseDto>
    suspend fun getCategoryCertList(isFavorite: Boolean, jobs: String): ApiResponse<CertListResponseDto>
    suspend fun getCertInfo(certificationId: Long): ApiResponse<GetCertInfoResponseDto>
    suspend fun getTop3TrackCertList(): ApiResponse<List<Top3CertListResponseDto>>
    suspend fun getTop3JobCertList(): ApiResponse<List<Top3CertListResponseDto>>
}
