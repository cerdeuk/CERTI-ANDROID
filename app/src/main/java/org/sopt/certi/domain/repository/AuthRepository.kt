package org.sopt.certi.domain.repository

import org.sopt.certi.domain.model.user.SignUpResult
import org.sopt.certi.domain.model.user.UserAuth
import org.sopt.certi.domain.model.user.UserInformationAuth

interface AuthRepository {
    suspend fun signIn(accessToken: String, socialType: String): Result<UserAuth>
    suspend fun signUp(
        preSignupToken: String,
        userInformation: UserInformationAuth?,
        university: String,
        grade: String,
        track: String,
        major: String,
        jobs: List<String>
    ): Result<SignUpResult>
}
