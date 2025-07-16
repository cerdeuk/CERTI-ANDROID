package org.sopt.certi.data.remote.datasource

import org.sopt.certi.data.remote.dto.base.ApiResponse
import org.sopt.certi.data.remote.dto.response.GetActivityListResponseDto

interface ActivityRemoteDataSource {
    suspend fun getActivityList(): ApiResponse<GetActivityListResponseDto>
}
