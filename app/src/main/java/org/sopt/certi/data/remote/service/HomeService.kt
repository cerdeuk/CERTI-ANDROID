package org.sopt.certi.data.remote.service

import org.sopt.certi.data.remote.dto.base.ApiResponse
import org.sopt.certi.data.remote.dto.base.NullableApiResponse
import org.sopt.certi.data.remote.dto.request.UpdatePreCertificationRequestDto
import org.sopt.certi.data.remote.dto.response.FavoriteListResponseDto
import org.sopt.certi.data.remote.dto.response.GetPreCertDayListResponseDto
import org.sopt.certi.data.remote.dto.response.GetPreCertMonthResponseDto
import org.sopt.certi.data.remote.dto.response.PreCertListResponseDto
import org.sopt.certi.data.remote.dto.response.UserInfoResponseDto
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface HomeService {
    @GET("/api/v1/user")
    suspend fun getUserInfo(): ApiResponse<UserInfoResponseDto>

    @GET("/api/v1/home/pre-certification")
    suspend fun getPreCertificationList(): NullableApiResponse<PreCertListResponseDto>

    @GET("/api/v1/home/favorite")
    suspend fun getFavoriteList(): NullableApiResponse<FavoriteListResponseDto>

    @POST("/api/v1/certification/{certificationId}/favorite")
    suspend fun toggleFavorite(
        @Path("certificationId") certificationId: Long
    ): NullableApiResponse<Unit>

    @GET("/api/v1/home/pre-certification/month")
    suspend fun getPreCertMonth(
        @Query("year") year: Int,
        @Query("month") month: Int
    ): ApiResponse<GetPreCertMonthResponseDto>

    @GET("/api/v1/home/pre-certification/day")
    suspend fun getPreCertDay(
        @Query("date") date: String
    ): ApiResponse<GetPreCertDayListResponseDto>

    @PATCH("/api/v1/home/pre-certification/{userPreCertificationId}")
    suspend fun updatePreCertification(
        @Path("userPreCertificationId") userPreCertificationId: Long,
        @Body requestBody: UpdatePreCertificationRequestDto
    ): NullableApiResponse<Unit>

    @DELETE("/api/v1/home/pre-certification/{certificationId}")
    suspend fun deletePreCertification(
        @Path("certificationId") preCertId: Long
    ): NullableApiResponse<Unit>
}
