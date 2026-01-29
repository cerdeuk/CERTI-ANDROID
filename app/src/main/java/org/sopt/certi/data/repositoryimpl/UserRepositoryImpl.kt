package org.sopt.certi.data.repositoryimpl

import org.sopt.certi.data.mapper.todomain.user.toDomain
import org.sopt.certi.data.remote.datasource.UserRemoteDataSource
import org.sopt.certi.data.remote.dto.request.AgreementRequestDto
import org.sopt.certi.data.remote.dto.request.ModifyInterestedJobRequestDto
import org.sopt.certi.data.remote.util.HttpResponseHandler.handleApiResponse
import org.sopt.certi.data.remote.util.HttpResponseHandler.handleNullableApiResponse
import org.sopt.certi.data.remote.util.safeApiCall
import org.sopt.certi.domain.model.user.InterestedJobListData
import org.sopt.certi.domain.model.user.MarketingPrivacyData
import org.sopt.certi.domain.model.user.MyPageInfo
import org.sopt.certi.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userRemoteDataSource: UserRemoteDataSource
) : UserRepository {
    override suspend fun checkNicknameValidation(keyword: String): Result<Unit> = safeApiCall {
        userRemoteDataSource.checkNicknameValidation(keyword)
            .handleNullableApiResponse()
            .getOrThrow()
    }

    override suspend fun getInterestedJobList(): Result<InterestedJobListData> = safeApiCall {
        userRemoteDataSource.getInterestedJobList()
            .handleApiResponse()
            .getOrThrow()
            .toDomain()
    }

    override suspend fun modifyInterestedJobList(jobNameList: List<String>): Result<Unit> = safeApiCall {
        val body = ModifyInterestedJobRequestDto(jobNameList)

        userRemoteDataSource.modifyInterestedJobList(body)
            .handleNullableApiResponse()
            .getOrThrow()
    }

    override suspend fun getUserTrack(): Result<String> = safeApiCall {
        userRemoteDataSource.getUserTrack()
            .handleApiResponse()
            .getOrThrow()
            .track
    }

    override suspend fun getMyPageInfo(): Result<MyPageInfo> = safeApiCall {
        userRemoteDataSource.getMyPageInfo()
            .handleApiResponse()
            .getOrThrow()
            .toDomain()
    }

    override suspend fun getMarketingPrivacyAgreement(): Result<MarketingPrivacyData> = safeApiCall {
        userRemoteDataSource.getMarketingPrivacyAgreement()
            .handleApiResponse()
            .getOrThrow()
            .toDomain()
    }

    override suspend fun patchMarketingAgreement(agreement: Boolean): Result<Unit> = safeApiCall {
        userRemoteDataSource.patchMarketingAgreement(AgreementRequestDto(agreement))
            .handleNullableApiResponse()
            .getOrThrow()
    }

    override suspend fun patchPrivacyAgreement(agreement: Boolean): Result<Unit> = safeApiCall {
        userRemoteDataSource.patchPrivacyAgreement(AgreementRequestDto(agreement))
            .handleNullableApiResponse()
            .getOrThrow()
    }
}
