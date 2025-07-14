package org.sopt.certi.data.remote.datasource

import org.sopt.certi.data.remote.dto.base.ApiResponse
import org.sopt.certi.data.remote.dto.request.SignInRequestDto
import org.sopt.certi.data.remote.dto.response.SignInResponseDto

interface AuthRemoteDataSource{
    suspend fun signIn(signInRequest: SignInRequestDto): ApiResponse<SignInResponseDto>
}