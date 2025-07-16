package org.sopt.certi.data.remote.datasource

import org.sopt.certi.data.remote.dto.base.ApiResponse
import org.sopt.certi.data.remote.dto.response.GetAcquisitionDetailResponseDto
import org.sopt.certi.data.remote.dto.response.GetAcquisitionListResponseDto

interface AcquisitionRemoteDataSource {
    suspend fun getAcquisitionList(): ApiResponse<GetAcquisitionListResponseDto>
    suspend fun getAcquisitionDetail(acquisitionId: Long): ApiResponse<GetAcquisitionDetailResponseDto>
}
