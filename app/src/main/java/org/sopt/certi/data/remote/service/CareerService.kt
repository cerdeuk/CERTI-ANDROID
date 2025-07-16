package org.sopt.certi.data.remote.service

import org.sopt.certi.data.remote.dto.base.ApiResponse
import org.sopt.certi.data.remote.dto.response.GetCareersResponseDto
import retrofit2.http.GET

interface CareerService {
    @GET("/api/v1/careers")
    suspend fun getCareerList(): ApiResponse<GetCareersResponseDto>
}
