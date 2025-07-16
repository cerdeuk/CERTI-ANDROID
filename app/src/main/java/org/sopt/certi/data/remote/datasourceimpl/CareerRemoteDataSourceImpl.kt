package org.sopt.certi.data.remote.datasourceimpl

import org.sopt.certi.data.remote.datasource.CareerRemoteDataSource
import org.sopt.certi.data.remote.dto.base.NullableApiResponse
import org.sopt.certi.data.remote.dto.request.AddActivityCareerRequestDto
import org.sopt.certi.data.remote.service.CareerService
import javax.inject.Inject

class CareerRemoteDataSourceImpl @Inject constructor(
    private val careerService: CareerService
) : CareerRemoteDataSource {
    override suspend fun addCareer(startAt: String, endAt: String, place: String, name: String, description: String): NullableApiResponse<Unit> =
        careerService.addCareers(addCareerRequest = AddActivityCareerRequestDto(startAt, endAt, place, name, description))
}
