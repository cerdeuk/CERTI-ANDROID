package org.sopt.certi.data.remote.datasourceimpl

import org.sopt.certi.data.remote.datasource.AuthRemoteDataSource
import org.sopt.certi.data.remote.dto.base.ApiResponse
import org.sopt.certi.data.remote.dto.request.SignInRequestDto
import org.sopt.certi.data.remote.dto.request.SignUpRequestDto
import org.sopt.certi.data.remote.dto.response.SignInResponseDto
import org.sopt.certi.data.remote.dto.response.SignUpResponseDto
import org.sopt.certi.data.remote.service.AuthService
import javax.inject.Inject

class AuthRemoteDataSourceImpl @Inject constructor(
    private val authService: AuthService
) : AuthRemoteDataSource {
    override suspend fun signIn(accessToken: String, socialType: String): ApiResponse<SignInResponseDto> =
        authService.signIn(signInRequest = SignInRequestDto(accessToken, socialType))

    override suspend fun signUp(accessToken: String, signUpRequest: SignUpRequestDto): ApiResponse<SignUpResponseDto> =
        authService.signUp(accessToken, signUpRequest)
}
