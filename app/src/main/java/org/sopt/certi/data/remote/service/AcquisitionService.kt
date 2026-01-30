package org.sopt.certi.data.remote.service

import org.sopt.certi.data.remote.dto.base.ApiResponse
import org.sopt.certi.data.remote.dto.base.NullableApiResponse
import org.sopt.certi.data.remote.dto.request.UpdateAcquisitionRequestDto
import retrofit2.http.POST
import org.sopt.certi.data.remote.dto.response.GetAcquisitionDetailResponseDto
import org.sopt.certi.data.remote.dto.response.GetAcquisitionListResponseDto
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path

interface AcquisitionService {
    @POST("api/v1/acquisition/{certificationId}")
    suspend fun acquiredCert(@Path("certificationId") certificationId: Long): NullableApiResponse<Boolean>

    @GET("/api/v1/acquisition")
    suspend fun getAcquisitionList(): ApiResponse<GetAcquisitionListResponseDto>

    @GET("/api/v1/acquisition/{acquisitionId}")
    suspend fun getAcquisitionDetail(
        @Path("acquisitionId") acquisitionId: Long
    ): ApiResponse<GetAcquisitionDetailResponseDto>

    @DELETE("/api/v1/acquisition/{acquisitionId}")
    suspend fun deleteAcquisition(
        @Path("acquisitionId") acquisitionId: Long
    ): NullableApiResponse<Unit>

    @PATCH("/api/v1/acquisition/{acquisitionId}")
    suspend fun updateAcquisition(
        @Path("acquisitionId") acquisitionId: Long,
        @Body request: UpdateAcquisitionRequestDto
    ): NullableApiResponse<Unit>
}
