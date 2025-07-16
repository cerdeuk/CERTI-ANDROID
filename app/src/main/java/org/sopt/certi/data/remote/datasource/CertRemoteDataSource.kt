package org.sopt.certi.data.remote.datasource

import org.sopt.certi.data.remote.dto.base.ApiResponse
import org.sopt.certi.data.remote.dto.response.GetCertInfoResponseDto
import org.sopt.certi.data.remote.dto.response.GetRecommendCertResponseDto
import org.sopt.certi.data.remote.dto.response.CertListResponseDto

interface CertRemoteDataSource {
    suspend fun getRecommendCertList(): ApiResponse<GetRecommendCertResponseDto>
    suspend fun searchCertList(keyword: String): ApiResponse<CertListResponseDto>
    suspend fun getCategoryCertList(isFavorite: Boolean, jobs: String): ApiResponse<CertListResponseDto>
    suspend fun getCertInfo(certificationId: Long): ApiResponse<GetCertInfoResponseDto>
}
