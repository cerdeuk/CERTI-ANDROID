package org.sopt.certi.data.repositoryimpl

import org.sopt.certi.data.mapper.todomain.career.toDomain
import org.sopt.certi.data.remote.datasource.CareerRemoteDataSource
import org.sopt.certi.data.remote.util.HttpResponseHandler.handleApiResponse
import org.sopt.certi.data.remote.util.HttpResponseHandler.handleNullableApiResponse
import org.sopt.certi.data.remote.util.safeApiCall
import org.sopt.certi.domain.model.ActivityData
import org.sopt.certi.domain.repository.CareerRepository
import javax.inject.Inject

class CareerRepositoryImpl @Inject constructor(
    private val careerRemoteDataSource: CareerRemoteDataSource
) : CareerRepository {
    override suspend fun addCareer(startAt: String, endAt: String, place: String, name: String, description: String): Result<Unit> = safeApiCall {
        careerRemoteDataSource.addCareer(startAt, endAt, place, name, description)
            .handleNullableApiResponse()
            .getOrThrow()
    }

    override suspend fun getCareerList(): Result<List<ActivityData>> = safeApiCall {
        careerRemoteDataSource.getCareerList()
            .handleApiResponse()
            .getOrThrow()
            .toDomain()
    }

    override suspend fun deleteCareer(careerId: Long): Result<Unit> = safeApiCall {
        careerRemoteDataSource.deleteCareer(careerId)
            .handleNullableApiResponse()
            .getOrThrow()
    }

    override suspend fun editCareer(careerId: Long, startAt: String, endAt: String, place: String, name: String, description: String): Result<Unit> = safeApiCall {
        careerRemoteDataSource.editCareer(careerId, startAt, endAt, place, name, description)
            .handleNullableApiResponse()
            .getOrThrow()
    }
}
