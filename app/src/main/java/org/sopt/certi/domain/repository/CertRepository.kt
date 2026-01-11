package org.sopt.certi.domain.repository

import org.sopt.certi.domain.model.certification.CertificationData
import org.sopt.certi.domain.model.certification.CertificationListData

interface CertRepository {
    suspend fun getRecommendCertList(): Result<CertificationListData>
    suspend fun searchCertList(keyword: String): Result<List<CertificationData>>
    suspend fun getJobCertList(isFavorite: Boolean, jobs: String): Result<List<CertificationData>>
    suspend fun getTrackCertList(isFavorite: Boolean, tracks: String): Result<List<CertificationData>>
    suspend fun getCertInfo(certificationId: Long): Result<CertificationData>
    suspend fun getTop3TrackCertList(): Result<List<CertificationData>>
    suspend fun getTop3JobCertList(): Result<List<CertificationData>>
}
