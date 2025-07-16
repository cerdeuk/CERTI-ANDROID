package org.sopt.certi.data.remote.datasource

import org.sopt.certi.data.remote.dto.base.ApiResponse
import org.sopt.certi.data.remote.dto.response.GetCertInfoResponseDto
import org.sopt.certi.data.remote.dto.response.GetRecommendCertResponseDto

interface CertRemoteDataSource {
    suspend fun getRecommendCertList(): ApiResponse<GetRecommendCertResponseDto>
    suspend fun getCertInfo(certificationId: Long): ApiResponse<GetCertInfoResponseDto>
}
