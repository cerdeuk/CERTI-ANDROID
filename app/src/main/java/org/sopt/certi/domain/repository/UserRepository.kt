package org.sopt.certi.domain.repository

import org.sopt.certi.domain.model.user.InterestedJobListData
import org.sopt.certi.domain.model.user.MyPageInfo

interface UserRepository {
    suspend fun checkNicknameValidation(keyword: String): Result<Unit>
    suspend fun getInterestedJobList(): Result<InterestedJobListData>
    suspend fun modifyInterestedJobList(jobNameList: List<String>): Result<Unit>
    suspend fun getUserTrack(): Result<String>
    suspend fun getMyPageInfo(): Result<MyPageInfo>
    suspend fun putUniversity(university: String): Result<Unit>
    suspend fun putMajor(major: String): Result<Unit>
}
