package org.sopt.certi.data.remote.datasource

import org.sopt.certi.data.remote.dto.base.ApiResponse
import org.sopt.certi.data.remote.dto.response.FavoriteListResponseDto
import org.sopt.certi.data.remote.dto.response.GetRecommendCertResponseDto
import org.sopt.certi.data.remote.dto.response.PreCertListResponseDto
import org.sopt.certi.data.remote.dto.response.UserInfoResponseDto

interface HomeRemoteDataSource {

    suspend fun getUserInfo(): ApiResponse<UserInfoResponseDto>
    suspend fun getRecommendedCertList(): ApiResponse<GetRecommendCertResponseDto>
    suspend fun getPreCertificationList(): ApiResponse<PreCertListResponseDto>
    suspend fun getFavoriteList(): ApiResponse<FavoriteListResponseDto>
    suspend fun toggleFavorite(certificationId: Long): ApiResponse<Unit>


}

