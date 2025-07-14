package org.sopt.certi.data.remote.service

import org.sopt.certi.data.remote.dto.base.ApiResponse
import org.sopt.certi.data.remote.dto.request.SignInRequestDto
import org.sopt.certi.data.remote.dto.response.SignInResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("/api/v1/auth/sign-in")
    suspend fun signIn(
        @Body signInRequest: SignInRequestDto
    ): ApiResponse<SignInResponseDto>
}
