package org.sopt.certi.data.remote.datasourceimpl

import org.sopt.certi.data.remote.datasource.AcquisitionRemoteDataSource
import org.sopt.certi.data.remote.dto.base.ApiResponse
import org.sopt.certi.data.remote.dto.response.AcquisitionListWrapperDto
import org.sopt.certi.data.remote.service.AcquisitionService
import javax.inject.Inject

class AcquisitionRemoteDataSourceImpl @Inject constructor(
    private val acquisitionService: AcquisitionService
) : AcquisitionRemoteDataSource {
    override suspend fun getAcquisitionList(): ApiResponse<AcquisitionListWrapperDto> =
        acquisitionService.getAcquisitionList()
}
