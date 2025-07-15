package org.sopt.certi.data.remote.datasource

import org.sopt.certi.data.remote.dto.base.ApiResponse
import org.sopt.certi.data.remote.dto.response.GetRecommendCertResponseDto

interface CertRemoteDataSource {
    suspend fun getRecommendCertList(): ApiResponse<GetRecommendCertResponseDto>
}
