package org.sopt.certi.data.remote.datasource

import org.sopt.certi.data.remote.dto.base.ApiResponse
import org.sopt.certi.data.remote.dto.response.GetCareersResponseDto

interface CareerRemoteDataSource {
    suspend fun getCareerList(): ApiResponse<GetCareersResponseDto>
}
