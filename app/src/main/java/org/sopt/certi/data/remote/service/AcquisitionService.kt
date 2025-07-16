package org.sopt.certi.data.remote.service

import org.sopt.certi.data.remote.dto.base.ApiResponse
import org.sopt.certi.data.remote.dto.response.GetAcquisitionListResponseDto
import retrofit2.http.GET

interface AcquisitionService {
    @GET("/api/v1/acquisition")
    suspend fun getAcquisitionList(): ApiResponse<GetAcquisitionListResponseDto>
}
