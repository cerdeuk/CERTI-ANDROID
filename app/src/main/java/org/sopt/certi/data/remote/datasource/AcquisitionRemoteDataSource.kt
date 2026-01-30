package org.sopt.certi.data.remote.datasource

import org.sopt.certi.data.remote.dto.base.ApiResponse
import org.sopt.certi.data.remote.dto.base.NullableApiResponse
import org.sopt.certi.data.remote.dto.request.UpdateAcquisitionRequestDto
import org.sopt.certi.data.remote.dto.response.GetAcquisitionDetailResponseDto
import org.sopt.certi.data.remote.dto.response.GetAcquisitionListResponseDto

interface AcquisitionRemoteDataSource {
    suspend fun acquiredCert(certificationId: Long): NullableApiResponse<Boolean>
    suspend fun getAcquisitionList(): ApiResponse<GetAcquisitionListResponseDto>
    suspend fun getAcquisitionDetail(acquisitionId: Long): ApiResponse<GetAcquisitionDetailResponseDto>
    suspend fun deleteAcquisition(acquisitionId: Long): NullableApiResponse<Unit>
    suspend fun updateAcquisition(acquisitionId: Long, request: UpdateAcquisitionRequestDto): NullableApiResponse<Unit>
}
