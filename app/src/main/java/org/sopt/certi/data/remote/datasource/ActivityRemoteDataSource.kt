package org.sopt.certi.data.remote.datasource

import org.sopt.certi.data.remote.dto.base.NullableApiResponse

interface ActivityRemoteDataSource {
    suspend fun addActivity(
        startAt: String,
        endAt: String,
        place: String,
        name: String,
        description: String
    ): NullableApiResponse<Unit>
}
