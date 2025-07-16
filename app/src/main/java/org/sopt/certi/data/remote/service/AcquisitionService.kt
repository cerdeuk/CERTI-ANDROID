package org.sopt.certi.data.remote.service

import org.sopt.certi.data.remote.dto.base.ApiResponse
import org.sopt.certi.data.remote.dto.response.GetAcquisitionDetailResponseDto
import org.sopt.certi.data.remote.dto.response.GetAcquisitionListResponseDto
import retrofit2.http.GET
import retrofit2.http.Path

interface AcquisitionService {
    @GET("/api/v1/acquisition")
    suspend fun getAcquisitionList(): ApiResponse<GetAcquisitionListResponseDto>

    @GET("/api/v1/acquisition/{acquisitionId}")
    suspend fun getAcquisitionDetail(
        @Path("acquisitionId") acquisitionId: Long
    ): ApiResponse<GetAcquisitionDetailResponseDto>
}
