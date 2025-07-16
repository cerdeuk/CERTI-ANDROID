package org.sopt.certi.domain.repository

import org.sopt.certi.data.remote.dto.base.NullableApiResponse

interface PreCertEditRepository {
    suspend fun deletePreCertification(certificationId: Long): Result<Unit>Unit>
}
