package org.sopt.certi.data.remote.service

import org.sopt.certi.data.remote.dto.base.ApiResponse
import org.sopt.certi.data.remote.dto.base.NullableApiResponse
import org.sopt.certi.data.remote.dto.request.ModifyInterestedJobRequestDto
import org.sopt.certi.data.remote.dto.response.GetInterestJobListResponseDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserService {
    @GET("/api/v1/user/job")
    suspend fun getInterestedJobList(): ApiResponse<GetInterestJobListResponseDto>

    @POST("api/v1/user/job")
    suspend fun modifyInterestedJobList(@Body body: ModifyInterestedJobRequestDto): NullableApiResponse<Unit>
}
