package org.sopt.certi.data.remote.datasourceimpl

import org.sopt.certi.data.remote.datasource.AcquisitionRemoteDataSource
import org.sopt.certi.data.remote.dto.base.NullableApiResponse
import org.sopt.certi.data.remote.service.AcquisitionService
import javax.inject.Inject

class AcquisitionRemoteDataSourceImpl @Inject constructor(
    private val acquisitionService: AcquisitionService
) : AcquisitionRemoteDataSource {
    override suspend fun acquiredCert(certificationId: Long): NullableApiResponse<Boolean> =
        acquisitionService.acquiredCert(certificationId)
}
