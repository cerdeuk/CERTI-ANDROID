package org.sopt.certi.data.remote.service

import org.sopt.certi.data.remote.dto.base.ApiResponse
import org.sopt.certi.data.remote.dto.request.SignInRequestDto
import org.sopt.certi.data.remote.dto.request.SignUpRequestDto
import org.sopt.certi.data.remote.dto.response.SearchMajorResponseDto
import org.sopt.certi.data.remote.dto.response.SearchUnivResponseDto
import org.sopt.certi.data.remote.dto.response.SignInResponseDto
import org.sopt.certi.data.remote.dto.response.SignUpResponseDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthService {
    @POST("/api/v1/auth/sign-in")
    suspend fun signIn(
        @Body signInRequest: SignInRequestDto
    ): ApiResponse<SignInResponseDto>

    @POST("/api/v1/auth/sign-up")
    suspend fun signUp(
        @Header("Authorization") accessToken: String,
        @Body signUpRequest: SignUpRequestDto
    ): ApiResponse<SignUpResponseDto>

    @GET("/api/v1/university/search")
    suspend fun searchUniv(
        @Query("keyword") keyword: String
    ): ApiResponse<SearchUnivResponseDto>

    @GET("/api/v1/major/search")
    suspend fun searchMajor(
        @Query("keyword") keyword: String
    ): ApiResponse<SearchMajorResponseDto>
}
