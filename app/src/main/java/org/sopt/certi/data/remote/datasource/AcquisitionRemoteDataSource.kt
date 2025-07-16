package org.sopt.certi.data.remote.datasource

import org.sopt.certi.data.remote.dto.base.ApiResponse
import org.sopt.certi.data.remote.dto.response.AcquisitionListWrapperDto

interface AcquisitionRemoteDataSource {
    suspend fun getAcquisitionList(): ApiResponse<AcquisitionListWrapperDto>
}
