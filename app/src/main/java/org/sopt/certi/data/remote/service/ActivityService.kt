package org.sopt.certi.data.remote.service

import org.sopt.certi.data.remote.dto.base.ApiResponse
import org.sopt.certi.data.remote.dto.response.GetActivityListResponseDto
import retrofit2.http.GET

interface ActivityService {
    @GET("/api/v1/activity")
    suspend fun getActivityList(): ApiResponse<GetActivityListResponseDto>
}
