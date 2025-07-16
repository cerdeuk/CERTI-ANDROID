package org.sopt.certi.data.remote.datasourceimpl

import org.sopt.certi.data.remote.datasource.UserRemoteDataSource
import org.sopt.certi.data.remote.dto.base.ApiResponse
import org.sopt.certi.data.remote.dto.base.NullableApiResponse
import org.sopt.certi.data.remote.dto.request.ModifyInterestedJobRequestDto
import org.sopt.certi.data.remote.dto.response.GetInterestJobListResponseDto
import org.sopt.certi.data.remote.service.UserService
import javax.inject.Inject

class UserRemoteDataSourceImpl @Inject constructor(
    private val userService: UserService
) : UserRemoteDataSource {
    override suspend fun getInterestedJobList(): ApiResponse<GetInterestJobListResponseDto> {
        return userService.getInterestedJobList()
    }

    override suspend fun modifyInterestedJobList(jobNameList: ModifyInterestedJobRequestDto): NullableApiResponse<Unit> {
        return userService.modifyInterestedJobList(jobNameList)
    }
}
