package org.sopt.certi.domain.repository

import org.sopt.certi.domain.model.certification.CertificationData
import org.sopt.certi.domain.model.certification.PreCertDayData
import org.sopt.certi.domain.model.user.UserInfoData

interface HomeRepository {
    suspend fun getUserInfo(): Result<UserInfoData>
    suspend fun getRecommendedCertList(): Result<List<CertificationData>>
    suspend fun getPreCertificationList(): Result<List<CertificationData>>
    suspend fun getFavoriteList(): Result<List<CertificationData>>
    suspend fun toggleFavorite(certificationId: Long): Result<Unit>
    suspend fun getPreCertMonth(year: Int, month: Int): Result<List<Int>>
    suspend fun getPreCertDay(date: String): Result<PreCertDayData>
    suspend fun updatePreCertification(certificationId: Long, testDate: String, city: String, state: String): Result<Unit>
}
