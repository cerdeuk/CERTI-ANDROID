package org.sopt.certi.data.remote.service

import org.sopt.certi.data.remote.dto.base.ApiResponse
import org.sopt.certi.data.remote.dto.base.NullableApiResponse
import org.sopt.certi.data.remote.dto.response.GetCareersResponseDto
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path

interface CareerService {
    @GET("/api/v1/careers")
    suspend fun getCareerList(): ApiResponse<GetCareersResponseDto>

    @DELETE("/api/v1/careers/{career-id}")
    suspend fun deleteCareer(
        @Path("career-id") careerId: Long
    ): NullableApiResponse<Unit>
}
