package org.sopt.certi.data.remote.datasourceimpl

import org.sopt.certi.data.remote.datasource.ActivityRemoteDataSource
import org.sopt.certi.data.remote.dto.base.NullableApiResponse
import org.sopt.certi.data.remote.dto.request.AddActivityCareerRequestDto
import org.sopt.certi.data.remote.service.ActivityService
import javax.inject.Inject

class ActivityRemoteDataSourceImpl @Inject constructor(
    private val activityService: ActivityService
) : ActivityRemoteDataSource {
    override suspend fun addActivity(startAt: String, endAt: String, place: String, name: String, description: String): NullableApiResponse<Unit> =
        activityService.addActivity(addActivityRequest = AddActivityCareerRequestDto(startAt, endAt, place, name, description))
}
