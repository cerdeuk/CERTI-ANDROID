package org.sopt.certi.domain.repository

interface ActivityRepository {
    suspend fun addActivity(
        startAt: String,
        endAt: String,
        place: String,
        name: String,
        description: String
    ): Result<Unit>
}
