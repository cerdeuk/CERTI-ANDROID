package org.sopt.certi.data.remote.datasource

import org.sopt.certi.data.remote.dto.base.ApiResponse
import org.sopt.certi.data.remote.dto.base.NullableApiResponse
import org.sopt.certi.data.remote.dto.request.ModifyInterestedJobRequestDto
import org.sopt.certi.data.remote.dto.request.PutPersonalInfoRequestDto
import org.sopt.certi.data.remote.dto.response.GetInterestJobListResponseDto
import org.sopt.certi.data.remote.dto.response.GetPersonalInfoResponseDto
import org.sopt.certi.data.remote.dto.response.GetUserTrackResponseDto

interface UserRemoteDataSource {
    suspend fun checkNicknameValidation(keyword: String): NullableApiResponse<Unit>
    suspend fun getInterestedJobList(): ApiResponse<GetInterestJobListResponseDto>
    suspend fun modifyInterestedJobList(jobNameList: ModifyInterestedJobRequestDto): NullableApiResponse<Unit>
    suspend fun getUserTrack(): ApiResponse<GetUserTrackResponseDto>
    suspend fun getPersonalInfo(): ApiResponse<GetPersonalInfoResponseDto>
    suspend fun putPersonalInfo(request: PutPersonalInfoRequestDto): NullableApiResponse<Unit>
}
