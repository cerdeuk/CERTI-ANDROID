package org.sopt.certi.data.remote.service

import org.sopt.certi.data.remote.dto.base.ApiResponse
import org.sopt.certi.data.remote.dto.response.GetInterestJobListResponseDto
import retrofit2.http.GET

interface UserService {
    @GET("/api/v1/user/job")
    suspend fun getInterestedJobList(): ApiResponse<GetInterestJobListResponseDto>
}