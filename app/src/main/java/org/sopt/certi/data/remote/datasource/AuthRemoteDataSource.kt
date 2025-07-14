package org.sopt.certi.data.remote.datasource

import org.sopt.certi.data.remote.dto.base.ApiResponse
import org.sopt.certi.data.remote.dto.response.SignInResponseDto

interface AuthRemoteDataSource {
    suspend fun signIn(accessToken: String, socialType: String): ApiResponse<SignInResponseDto>
}
