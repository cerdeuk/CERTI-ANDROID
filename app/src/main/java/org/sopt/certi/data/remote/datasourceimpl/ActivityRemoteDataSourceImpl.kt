package org.sopt.certi.data.remote.datasourceimpl

import org.sopt.certi.data.remote.datasource.ActivityRemoteDataSource
import org.sopt.certi.data.remote.dto.base.ApiResponse
import org.sopt.certi.data.remote.dto.response.GetActivityListResponseDto
import org.sopt.certi.data.remote.service.ActivityService
import javax.inject.Inject

class ActivityRemoteDataSourceImpl @Inject constructor(
    private val activityService: ActivityService
) : ActivityRemoteDataSource {
    override suspend fun getActivityList(): ApiResponse<GetActivityListResponseDto> =
        activityService.getActivityList()
}
