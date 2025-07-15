package org.sopt.certi.domain.repository

import org.sopt.certi.domain.model.CertificationData
import org.sopt.certi.domain.model.UserInfoData

interface HomeRepository {
    suspend fun getUserInfo(): Result<UserInfoData>
    suspend fun getPreCertificationList(): Result<List<CertificationData>>
    suspend fun getFavoriteList(): Result<List<CertificationData>>
}
