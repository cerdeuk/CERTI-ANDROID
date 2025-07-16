package org.sopt.certi.data.remote.datasourceimpl

import org.sopt.certi.data.remote.datasource.PreCertEditDataSource
import org.sopt.certi.data.remote.dto.base.ApiResponse
import org.sopt.certi.data.remote.dto.base.NullableApiResponse
import org.sopt.certi.data.remote.service.PreCertEditService
import javax.inject.Inject

class PreCertificationDataSourceImpl @Inject constructor(
    private val preCertEditService: PreCertEditService
) : PreCertEditDataSource {
    override suspend fun deletePreCertification(certificationId: Long): NullableApiResponse<Unit> =
        preCertEditService.deletePreCertification(certificationId)
}
