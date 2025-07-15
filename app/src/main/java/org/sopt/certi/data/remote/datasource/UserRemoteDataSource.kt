package org.sopt.certi.data.remote.datasource

import org.sopt.certi.data.remote.dto.base.ApiResponse
import org.sopt.certi.data.remote.dto.response.GetInterestJobListResponseDto

interface UserRemoteDataSource {
    suspend fun getInterestedJobList() : ApiResponse<GetInterestJobListResponseDto>
}