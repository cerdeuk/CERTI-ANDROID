package org.sopt.certi.data.remote.datasourceimpl

import org.sopt.certi.data.remote.datasource.CareerRemoteDataSource
import org.sopt.certi.data.remote.dto.base.ApiResponse
import org.sopt.certi.data.remote.dto.base.NullableApiResponse
import org.sopt.certi.data.remote.dto.request.ActivityCareerRequestDto
import org.sopt.certi.data.remote.dto.response.GetCareersResponseDto
import org.sopt.certi.data.remote.service.CareerService
import javax.inject.Inject

class CareerRemoteDataSourceImpl @Inject constructor(
    private val careerService: CareerService
) : CareerRemoteDataSource {
    override suspend fun addCareer(startAt: String, endAt: String, place: String, name: String, description: String): NullableApiResponse<Unit> =
        careerService.addCareers(addCareerRequest = ActivityCareerRequestDto(startAt, endAt, place, name, description))
    override suspend fun getCareerList(): ApiResponse<GetCareersResponseDto> = careerService.getCareerList()
    override suspend fun deleteCareer(careerId: Long): NullableApiResponse<Unit> = careerService.deleteCareer(careerId)
    override suspend fun editCareer(careerId: Long, startAt: String, endAt: String, place: String, name: String, description: String): NullableApiResponse<Unit> =
        careerService.editCareer(careerId, ActivityCareerRequestDto(startAt, endAt, place, name, description))
}
