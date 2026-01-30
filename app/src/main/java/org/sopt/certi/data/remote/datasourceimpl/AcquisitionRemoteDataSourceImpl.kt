package org.sopt.certi.data.remote.datasourceimpl

import org.sopt.certi.data.remote.datasource.AcquisitionRemoteDataSource
import org.sopt.certi.data.remote.dto.base.ApiResponse
import org.sopt.certi.data.remote.dto.base.NullableApiResponse
import org.sopt.certi.data.remote.dto.request.UpdateAcquisitionRequestDto
import org.sopt.certi.data.remote.dto.response.GetAcquisitionDetailResponseDto
import org.sopt.certi.data.remote.dto.response.GetAcquisitionListResponseDto
import org.sopt.certi.data.remote.service.AcquisitionService
import javax.inject.Inject

class AcquisitionRemoteDataSourceImpl @Inject constructor(
    private val acquisitionService: AcquisitionService
) : AcquisitionRemoteDataSource {
    override suspend fun acquiredCert(certificationId: Long): NullableApiResponse<Boolean> =
        acquisitionService.acquiredCert(certificationId)

    override suspend fun getAcquisitionList(): ApiResponse<GetAcquisitionListResponseDto> =
        acquisitionService.getAcquisitionList()

    override suspend fun getAcquisitionDetail(acquisitionId: Long): ApiResponse<GetAcquisitionDetailResponseDto> =
        acquisitionService.getAcquisitionDetail(acquisitionId)

    override suspend fun deleteAcquisition(acquisitionId: Long): NullableApiResponse<Unit> =
        acquisitionService.deleteAcquisition(acquisitionId)

    override suspend fun updateAcquisition(acquisitionId: Long, request: UpdateAcquisitionRequestDto): NullableApiResponse<Unit> =
        acquisitionService.updateAcquisition(acquisitionId, request)
}
