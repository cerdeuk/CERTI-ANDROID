package org.sopt.certi.data.remote.datasource

import org.sopt.certi.data.remote.dto.base.ApiResponse
import org.sopt.certi.data.remote.dto.request.SignUpRequestDto
import org.sopt.certi.data.remote.dto.response.SearchUnivResponseDto
import org.sopt.certi.data.remote.dto.response.SignInResponseDto
import org.sopt.certi.data.remote.dto.response.SignUpResponseDto

interface AuthRemoteDataSource {
    suspend fun signIn(accessToken: String, socialType: String): ApiResponse<SignInResponseDto>
    suspend fun signUp(accessToken: String, signUpRequest: SignUpRequestDto): ApiResponse<SignUpResponseDto>
    suspend fun searchUniv(keyword: String): ApiResponse<SearchUnivResponseDto>
}
