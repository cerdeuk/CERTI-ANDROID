package org.sopt.certi.data.remote.service

import org.sopt.certi.data.remote.dto.base.ApiResponse
import org.sopt.certi.data.remote.dto.base.NullableApiResponse
import org.sopt.certi.data.remote.dto.response.GetActivityListResponseDto
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path

interface ActivityService {
    @GET("/api/v1/activity")
    suspend fun getActivityList(): ApiResponse<GetActivityListResponseDto>

    @DELETE("/api/v1/activity/{activity-id}")
    suspend fun deleteActivity(
        @Path("activity-id") activityId: Long
    ): NullableApiResponse<Unit>
}
