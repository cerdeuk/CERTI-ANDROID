package org.sopt.certi.data.remote.service

import org.sopt.certi.data.remote.dto.base.ApiResponse
import org.sopt.certi.data.remote.dto.base.NullableApiResponse
import org.sopt.certi.data.remote.dto.request.AddActivityCareerRequestDto
import retrofit2.http.Body
import retrofit2.http.POST
import org.sopt.certi.data.remote.dto.response.GetActivityListResponseDto
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path

interface ActivityService {
    @POST("/api/v1/activity")
    suspend fun addActivity(
        @Body addActivityRequest: AddActivityCareerRequestDto
    ): NullableApiResponse<Unit>

    @GET("/api/v1/activity")
    suspend fun getActivityList(): ApiResponse<GetActivityListResponseDto>

    @DELETE("/api/v1/activity/{activity-id}")
    suspend fun deleteActivity(
        @Path("activity-id") activityId: Long
    ): NullableApiResponse<Unit>
}
