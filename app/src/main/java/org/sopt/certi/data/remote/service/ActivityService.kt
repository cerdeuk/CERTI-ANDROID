package org.sopt.certi.data.remote.service

import org.sopt.certi.data.remote.dto.base.NullableApiResponse
import org.sopt.certi.data.remote.dto.request.AddActivityCareerRequestDto
import retrofit2.http.Body
import retrofit2.http.POST

interface ActivityService {
    @POST("/api/v1/activity")
    suspend fun addActivity(
        @Body addActivityRequest: AddActivityCareerRequestDto
    ): NullableApiResponse<Unit>
}
