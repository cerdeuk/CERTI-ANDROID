package org.sopt.certi.data.remote.service

import org.sopt.certi.data.remote.dto.base.ApiResponse
import org.sopt.certi.data.remote.dto.base.NullableApiResponse
import org.sopt.certi.data.remote.dto.request.ModifyInterestedJobRequestDto
import org.sopt.certi.data.remote.dto.request.PutPersonalInfoRequestDto
import org.sopt.certi.data.remote.dto.response.GetInterestJobListResponseDto
import org.sopt.certi.data.remote.dto.response.GetPersonalInfoResponseDto
import org.sopt.certi.data.remote.dto.response.GetUserTrackResponseDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface UserService {
    @GET("/api/v1/user/validation")
    suspend fun checkNicknameValidation(
        @Query("keyword") keyword: String
    ): NullableApiResponse<Unit>

    @GET("/api/v1/user/job")
    suspend fun getInterestedJobList(): ApiResponse<GetInterestJobListResponseDto>

    @POST("api/v1/user/job")
    suspend fun modifyInterestedJobList(@Body body: ModifyInterestedJobRequestDto): NullableApiResponse<Unit>

    @GET("/api/v1/user/track")
    suspend fun getUserTrack(): ApiResponse<GetUserTrackResponseDto>

    @GET("/api/v1/user/pinfo")
    suspend fun getPersonalInfo(): ApiResponse<GetPersonalInfoResponseDto>

    @PUT("/api/v1/user/pinfo")
    suspend fun putPersonalInfo(@Body request: PutPersonalInfoRequestDto): NullableApiResponse<Unit>
}
