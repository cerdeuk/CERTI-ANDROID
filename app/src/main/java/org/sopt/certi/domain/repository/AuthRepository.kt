package org.sopt.certi.domain.repository

import org.sopt.certi.domain.model.UserPreAuth

interface AuthRepository {
    suspend fun signIn(accessToken: String, socialType: String): Result<UserPreAuth>
}
