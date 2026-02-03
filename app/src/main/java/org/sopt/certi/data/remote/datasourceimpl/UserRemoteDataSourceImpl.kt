package org.sopt.certi.data.remote.datasourceimpl

import org.sopt.certi.data.remote.datasource.UserRemoteDataSource
import org.sopt.certi.data.remote.dto.base.ApiResponse
import org.sopt.certi.data.remote.dto.base.NullableApiResponse
import org.sopt.certi.data.remote.dto.request.MajorRequestDto
import org.sopt.certi.data.remote.dto.request.ModifyInterestedJobRequestDto
import org.sopt.certi.data.remote.dto.request.PutPersonalInfoRequestDto
import org.sopt.certi.data.remote.dto.request.UniversityRequestDto
import org.sopt.certi.data.remote.dto.response.GetInterestJobListResponseDto
import org.sopt.certi.data.remote.dto.response.GetMyPageResponseDto
import org.sopt.certi.data.remote.dto.response.GetPersonalInfoResponseDto
import org.sopt.certi.data.remote.dto.response.GetUserTrackResponseDto
import org.sopt.certi.data.remote.dto.response.PresignedResponseDto
import org.sopt.certi.data.remote.service.UserService
import javax.inject.Inject

class UserRemoteDataSourceImpl @Inject constructor(
    private val userService: UserService
) : UserRemoteDataSource {
    override suspend fun checkNicknameValidation(keyword: String): NullableApiResponse<Unit> = userService.checkNicknameValidation(keyword)

    override suspend fun getInterestedJobList(): ApiResponse<GetInterestJobListResponseDto> {
        return userService.getInterestedJobList()
    }

    override suspend fun modifyInterestedJobList(jobNameList: ModifyInterestedJobRequestDto): NullableApiResponse<Unit> {
        return userService.modifyInterestedJobList(jobNameList)
    }

    override suspend fun getUserTrack(): ApiResponse<GetUserTrackResponseDto> =
        userService.getUserTrack()

    override suspend fun getMyPageInfo(): ApiResponse<GetMyPageResponseDto> =
        userService.getMyPageInfo()

    override suspend fun getPersonalInfo(): ApiResponse<GetPersonalInfoResponseDto> =
        userService.getPersonalInfo()

    override suspend fun putPersonalInfo(request: PutPersonalInfoRequestDto): NullableApiResponse<Unit> =
        userService.putPersonalInfo(request)

    override suspend fun getPresignedUrl(): ApiResponse<PresignedResponseDto> =
        userService.getPresignedUrl()

    override suspend fun putUniversity(university: UniversityRequestDto): NullableApiResponse<Unit> =
        userService.putUniversity(university)

    override suspend fun putMajor(major: MajorRequestDto): NullableApiResponse<Unit> =
        userService.putMajor(major)
}
