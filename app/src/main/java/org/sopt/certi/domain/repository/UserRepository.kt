package org.sopt.certi.domain.repository

import org.sopt.certi.domain.model.image.PresignedData
import org.sopt.certi.domain.model.user.InterestedJobListData
import org.sopt.certi.domain.model.user.MarketingPrivacyData
import org.sopt.certi.domain.model.user.MyPageInfo
import org.sopt.certi.domain.model.user.PersonalInfo

interface UserRepository {
    suspend fun checkNicknameValidation(keyword: String): Result<Unit>
    suspend fun getInterestedJobList(): Result<InterestedJobListData>
    suspend fun modifyInterestedJobList(jobNameList: List<String>): Result<Unit>
    suspend fun getUserTrack(): Result<String>
    suspend fun getMyPageInfo(): Result<MyPageInfo>
    suspend fun getPersonalInfo(): Result<PersonalInfo>
    suspend fun putPersonalInfo(request: PersonalInfo): Result<Unit>
    suspend fun getPresignedUrl(): Result<PresignedData>
    suspend fun putUniversity(university: String): Result<Unit>
    suspend fun putMajor(major: String): Result<Unit>
    suspend fun getMarketingPrivacyAgreement(): Result<MarketingPrivacyData>
    suspend fun patchMarketingAgreement(agreement: Boolean): Result<Unit>
    suspend fun patchPrivacyAgreement(agreement: Boolean): Result<Unit>
}
