package org.sopt.certi.data.remote.datasourceimpl

import org.sopt.certi.data.remote.datasource.HomeRemoteDataSource
import org.sopt.certi.data.remote.service.HomeService
import javax.inject.Inject

class HomeRemoteDataSourceImpl @Inject constructor(
    private val service: HomeService
) : HomeRemoteDataSource {
    override suspend fun getUserInfo() = service.getUserInfo()
    override suspend fun getPreCertificationList() = service.getPreCertificationList()
    override suspend fun getFavoriteList() = service.getFavoriteList()
    override suspend fun toggleFavorite(certificationId: Long) = service.toggleFavorite(certificationId)
    override suspend fun getPreCertMonth(year: Int, month: Int) = service.getPreCertMonth(year, month)
    override suspend fun getPreCertDay(date: String) = service.getPreCertDay(date)
}
