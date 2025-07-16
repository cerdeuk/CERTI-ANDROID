package org.sopt.certi.data.remote.datasource

import org.sopt.certi.data.remote.dto.base.ApiResponse
import org.sopt.certi.data.remote.dto.response.GetRecommendCertResponseDto
import org.sopt.certi.data.remote.dto.response.SearchCertListResponseDto

interface CertRemoteDataSource {
    suspend fun getRecommendCertList(): ApiResponse<GetRecommendCertResponseDto>
    suspend fun searchCertList(keyword: String): ApiResponse<SearchCertListResponseDto>
}
