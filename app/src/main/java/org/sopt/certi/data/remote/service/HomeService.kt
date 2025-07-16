package org.sopt.certi.data.remote.service

import org.sopt.certi.data.remote.dto.base.ApiResponse
import org.sopt.certi.data.remote.dto.base.NullableApiResponse
import org.sopt.certi.data.remote.dto.response.FavoriteListResponseDto
import org.sopt.certi.data.remote.dto.response.PreCertListResponseDto
import org.sopt.certi.data.remote.dto.response.UserInfoResponseDto
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

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
}
