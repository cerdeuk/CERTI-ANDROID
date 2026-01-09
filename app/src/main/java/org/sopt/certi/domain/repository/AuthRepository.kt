package org.sopt.certi.domain.repository

import org.sopt.certi.domain.model.user.SignUpResult
import org.sopt.certi.domain.model.user.UserAuth
import org.sopt.certi.domain.model.user.UserInformationAuth

interface AuthRepository {
    suspend fun signIn(accessToken: String, socialType: String): Result<UserAuth>
    suspend fun signUp(
        preSignupToken: String,
        userInformation: UserInformationAuth?,
        nickname: String,
        university: String,
        grade: String,
        track: String,
        major: String,
        jobs: List<String>
    ): Result<SignUpResult>
    suspend fun searchUniv(keyword: String): Result<List<String>>
    suspend fun searchMajor(keyword: String): Result<List<String>>
    suspend fun withDraw(): Result<Unit>
}
