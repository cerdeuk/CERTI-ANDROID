package org.sopt.certi.data.remote.datasource

import org.sopt.certi.data.remote.dto.base.ApiResponse
import org.sopt.certi.data.remote.dto.base.NullableApiResponse
import org.sopt.certi.data.remote.dto.request.UpdatePreCertificationRequestDto
import org.sopt.certi.data.remote.dto.response.FavoriteListResponseDto
import org.sopt.certi.data.remote.dto.response.GetPreCertDayListResponseDto
import org.sopt.certi.data.remote.dto.response.GetPreCertMonthResponseDto
import org.sopt.certi.data.remote.dto.response.PreCertListResponseDto
import org.sopt.certi.data.remote.dto.response.UserInfoResponseDto

interface HomeRemoteDataSource {
    suspend fun getUserInfo(): ApiResponse<UserInfoResponseDto>
    suspend fun getPreCertificationList(): NullableApiResponse<PreCertListResponseDto>
    suspend fun getFavoriteList(): NullableApiResponse<FavoriteListResponseDto>
    suspend fun toggleFavorite(certificationId: Long): NullableApiResponse<Unit>
    suspend fun getPreCertMonth(year: Int, month: Int): ApiResponse<GetPreCertMonthResponseDto>
    suspend fun getPreCertDay(date: String): ApiResponse<GetPreCertDayListResponseDto>
    suspend fun updatePreCertification(certificationId: Long, requestBody: UpdatePreCertificationRequestDto): NullableApiResponse<Unit>
    suspend fun deletePreCertification(preCertId: Long): NullableApiResponse<Unit>
}
