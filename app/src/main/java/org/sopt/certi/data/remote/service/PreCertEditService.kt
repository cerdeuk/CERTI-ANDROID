package org.sopt.certi.data.remote.service

import org.sopt.certi.data.remote.dto.base.NullableApiResponse
import retrofit2.http.DELETE
import retrofit2.http.Path

interface PreCertEditService {
    @DELETE("/api/v1/home/pre-certification/{certificationId}")
    suspend fun deletePreCertification(
        @Path("certificationId") certificationId: Long
    ): NullableApiResponse<Unit>
}
