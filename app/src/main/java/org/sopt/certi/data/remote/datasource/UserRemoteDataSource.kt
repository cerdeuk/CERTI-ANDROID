package org.sopt.certi.data.remote.datasource

import org.sopt.certi.data.remote.dto.base.ApiResponse
import org.sopt.certi.data.remote.dto.base.NullableApiResponse
import org.sopt.certi.data.remote.dto.request.MajorRequestDto
import org.sopt.certi.data.remote.dto.request.AgreementRequestDto
import org.sopt.certi.data.remote.dto.request.ModifyInterestedJobRequestDto
import org.sopt.certi.data.remote.dto.request.PutPersonalInfoRequestDto
import org.sopt.certi.data.remote.dto.request.UniversityRequestDto
import org.sopt.certi.data.remote.dto.response.GetInterestJobListResponseDto
import org.sopt.certi.data.remote.dto.response.GetPersonalInfoResponseDto
import org.sopt.certi.data.remote.dto.response.GetMyPageResponseDto
import org.sopt.certi.data.remote.dto.response.GetUserTrackResponseDto
import org.sopt.certi.data.remote.dto.response.MarketingPrivacyResponseDto
import org.sopt.certi.data.remote.dto.response.PresignedResponseDto

interface UserRemoteDataSource {
    suspend fun checkNicknameValidation(keyword: String): NullableApiResponse<Unit>
    suspend fun getInterestedJobList(): ApiResponse<GetInterestJobListResponseDto>
    suspend fun modifyInterestedJobList(jobNameList: ModifyInterestedJobRequestDto): NullableApiResponse<Unit>
    suspend fun getUserTrack(): ApiResponse<GetUserTrackResponseDto>
    suspend fun getMyPageInfo(): ApiResponse<GetMyPageResponseDto>
    suspend fun getPersonalInfo(): ApiResponse<GetPersonalInfoResponseDto>
    suspend fun putPersonalInfo(request: PutPersonalInfoRequestDto): NullableApiResponse<Unit>
    suspend fun getPresignedUrl(): ApiResponse<PresignedResponseDto>
    suspend fun putUniversity(university: UniversityRequestDto): NullableApiResponse<Unit>
    suspend fun putMajor(major: MajorRequestDto): NullableApiResponse<Unit>
    suspend fun getMarketingPrivacyAgreement(): ApiResponse<MarketingPrivacyResponseDto>
    suspend fun patchMarketingAgreement(agreement: AgreementRequestDto): NullableApiResponse<Unit>
    suspend fun patchPrivacyAgreement(agreement: AgreementRequestDto): NullableApiResponse<Unit>
}
