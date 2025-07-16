package org.sopt.certi.data.repositoryimpl

import org.sopt.certi.data.remote.datasource.CareerRemoteDataSource
import org.sopt.certi.data.remote.util.HttpResponseHandler.handleNullableApiResponse
import org.sopt.certi.data.remote.util.safeApiCall
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
}
