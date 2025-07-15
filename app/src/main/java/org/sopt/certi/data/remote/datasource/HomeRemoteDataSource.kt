package org.sopt.certi.data.remote.datasource

import org.sopt.certi.data.remote.dto.base.ApiResponse
import org.sopt.certi.data.remote.dto.response.FavoriteResponseDto
import org.sopt.certi.data.remote.dto.response.PreCertificationResponseDto
import org.sopt.certi.data.remote.dto.response.UserInfoResponseDto

interface HomeRemoteDataSource {
    suspend fun getUserInfo(): ApiResponse<UserInfoResponseDto>
    suspend fun getPreCertificationList(): ApiResponse<PreCertificationResponseDto>
    suspend fun getFavoriteList(): ApiResponse<FavoriteResponseDto>
}
