package org.sopt.certi.data.repositoryimpl

import org.sopt.certi.data.mapper.todomain.activity.toDomain
import org.sopt.certi.data.remote.datasource.ActivityRemoteDataSource
import org.sopt.certi.data.remote.util.HttpResponseHandler.handleApiResponse
import org.sopt.certi.data.remote.util.safeApiCall
import org.sopt.certi.domain.model.ActivityData
import org.sopt.certi.domain.repository.ActivityRepository
import javax.inject.Inject

class ActivityRepositoryImpl @Inject constructor(
    private val activityRemoteDataSource: ActivityRemoteDataSource
) : ActivityRepository {
    override suspend fun getActivityList(): Result<List<ActivityData>> = safeApiCall {
        activityRemoteDataSource.getActivityList()
            .handleApiResponse()
            .getOrThrow()
            .toDomain()
    }
}
