package org.sopt.certi.data.remote.datasourceimpl

import org.sopt.certi.data.remote.datasource.CareerRemoteDataSource
import org.sopt.certi.data.remote.dto.base.ApiResponse
import org.sopt.certi.data.remote.dto.response.GetCareersResponseDto
import org.sopt.certi.data.remote.service.CareerService
import javax.inject.Inject

class CareerRemoteDataSourceImpl @Inject constructor(
    private val careerService: CareerService
) : CareerRemoteDataSource {
    override suspend fun getCareerList(): ApiResponse<GetCareersResponseDto> = careerService.getCareerList()
}
