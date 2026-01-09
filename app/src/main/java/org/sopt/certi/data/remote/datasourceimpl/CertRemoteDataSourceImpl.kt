package org.sopt.certi.data.remote.datasourceimpl

import org.sopt.certi.data.remote.datasource.CertRemoteDataSource
import org.sopt.certi.data.remote.dto.base.ApiResponse
import org.sopt.certi.data.remote.dto.response.CertListResponseDto
import org.sopt.certi.data.remote.dto.response.GetCertInfoResponseDto
import org.sopt.certi.data.remote.dto.response.GetRecommendCertResponseDto
import org.sopt.certi.data.remote.dto.response.Top3CertListResponseDto
import org.sopt.certi.data.remote.service.CertService
import javax.inject.Inject

class CertRemoteDataSourceImpl @Inject constructor(
    private val certService: CertService
) : CertRemoteDataSource {
    override suspend fun getRecommendCertList(): ApiResponse<GetRecommendCertResponseDto> =
        certService.getRecommendCertList()

    override suspend fun getCertInfo(certificationId: Long): ApiResponse<GetCertInfoResponseDto> =
        certService.getCertInfo(certificationId)

    override suspend fun searchCertList(keyword: String): ApiResponse<CertListResponseDto> =
        certService.searchCertList(keyword)

    override suspend fun getCategoryCertList(isFavorite: Boolean, jobs: String): ApiResponse<CertListResponseDto> =
        certService.getJobCertList(isFavorite, jobs)

    override suspend fun getTop3TrackCertList(): ApiResponse<List<Top3CertListResponseDto>> =
        certService.getTop3TrackCertList()

    override suspend fun getTop3JobCertList(): ApiResponse<List<Top3CertListResponseDto>> =
        certService.getTop3JobCertList()
}
