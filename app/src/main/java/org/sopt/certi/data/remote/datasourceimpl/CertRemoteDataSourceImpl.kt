package org.sopt.certi.data.remote.datasourceimpl

import org.sopt.certi.data.remote.datasource.CertRemoteDataSource
import org.sopt.certi.data.remote.dto.base.ApiResponse
import org.sopt.certi.data.remote.dto.response.GetRecommendCertResponseDto
import org.sopt.certi.data.remote.dto.response.SearchCertListResponseDto
import org.sopt.certi.data.remote.service.CertService
import javax.inject.Inject

class CertRemoteDataSourceImpl @Inject constructor(
    private val certService: CertService
) : CertRemoteDataSource {
    override suspend fun getRecommendCertList(): ApiResponse<GetRecommendCertResponseDto> {
        return certService.getRecommendCertList()
    }

    override suspend fun searchCertList(keyword: String): ApiResponse<SearchCertListResponseDto> =
        certService.searchCertList(keyword)
}
