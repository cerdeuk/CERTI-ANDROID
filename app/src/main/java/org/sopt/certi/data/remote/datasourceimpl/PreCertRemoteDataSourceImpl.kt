package org.sopt.certi.data.remote.datasourceimpl

import org.sopt.certi.data.remote.datasource.PreCertRemoteDataSource
import org.sopt.certi.data.remote.dto.base.NullableApiResponse
import org.sopt.certi.data.remote.service.PreCertService
import javax.inject.Inject

class PreCertRemoteDataSourceImpl @Inject constructor(
    private val preCertService: PreCertService
) : PreCertRemoteDataSource {
    override suspend fun acquireExpectCert(certificationId: Long): NullableApiResponse<Boolean> =
        preCertService.acquireExpectCert(certificationId)
}