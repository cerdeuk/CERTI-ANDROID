package org.sopt.certi.data.remote.datasourceimpl

import org.sopt.certi.data.remote.datasource.PreCertEditRemoteDataSource
import org.sopt.certi.data.remote.dto.base.NullableApiResponse
import org.sopt.certi.data.remote.service.PreCertEditService
import javax.inject.Inject

class PreCertEditRemoteDataSourceImpl @Inject constructor(
    private val preCertEditService: PreCertEditService
) : PreCertEditRemoteDataSource {
    override suspend fun deletePreCertification(certificationId: Long): NullableApiResponse<Unit> =
        preCertEditService.deletePreCertification(certificationId)
}
