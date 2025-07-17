package org.sopt.certi.data.remote.service

import org.sopt.certi.data.remote.dto.base.NullableApiResponse
import org.sopt.certi.data.remote.dto.request.AddActivityCareerRequestDto
import retrofit2.http.Body
import retrofit2.http.POST

interface CareerService {
    @POST("/api/v1/careers")
    suspend fun addCareers(
        @Body addCareerRequest: AddActivityCareerRequestDto
    ): NullableApiResponse<Unit>
}
