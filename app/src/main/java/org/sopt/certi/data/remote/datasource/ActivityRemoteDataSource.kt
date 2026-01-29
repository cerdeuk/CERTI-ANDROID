package org.sopt.certi.data.remote.datasource

import org.sopt.certi.data.remote.dto.base.ApiResponse
import org.sopt.certi.data.remote.dto.base.NullableApiResponse
import org.sopt.certi.data.remote.dto.response.GetActivityListResponseDto

interface ActivityRemoteDataSource {
    suspend fun addActivity(
        startAt: String,
        endAt: String,
        place: String,
        name: String,
        description: String
    ): NullableApiResponse<Unit>
    suspend fun getActivityList(): ApiResponse<GetActivityListResponseDto>
    suspend fun deleteActivity(activityId: Long): NullableApiResponse<Unit>
    suspend fun editActivity(activityId: Long, startAt: String, endAt: String, place: String, name: String, description: String): NullableApiResponse<Unit>
}
