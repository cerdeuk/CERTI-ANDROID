package org.sopt.certi.data.remote.datasource

import org.sopt.certi.data.remote.dto.base.NullableApiResponse

interface CareerRemoteDataSource {
    suspend fun addCareer(
        startAt: String,
        endAt: String,
        place: String,
        name: String,
        description: String
    ): NullableApiResponse<Unit>
}
