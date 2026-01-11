package org.sopt.certi.domain.repository

import org.sopt.certi.domain.model.user.InterestedJobListData

interface UserRepository {
    suspend fun checkNicknameValidation(keyword: String): Result<Unit>
    suspend fun getInterestedJobList(): Result<InterestedJobListData>
    suspend fun modifyInterestedJobList(jobNameList: List<String>): Result<Unit>
    suspend fun getUserTrack(): Result<String>
}
