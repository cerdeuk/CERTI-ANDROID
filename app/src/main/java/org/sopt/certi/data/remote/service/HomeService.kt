package org.sopt.certi.data.remote.service

import org.sopt.certi.data.remote.dto.base.ApiResponse
import org.sopt.certi.data.remote.dto.response.FavoriteResponseDto
import org.sopt.certi.data.remote.dto.response.PreCertificationResponseDto
import org.sopt.certi.data.remote.dto.response.UserInfoResponseDto
import retrofit2.http.GET

interface HomeService {
    @GET("/api/v1/user")
    suspend fun getUserInfo(): ApiResponse<UserInfoResponseDto>

    @GET("/api/v1/home/pre-certification")
    suspend fun getPreCertificationList(): ApiResponse<PreCertificationResponseDto>

    @GET("/api/v1/home/favorite")
    suspend fun getFavoriteList(): ApiResponse<FavoriteResponseDto>
}
