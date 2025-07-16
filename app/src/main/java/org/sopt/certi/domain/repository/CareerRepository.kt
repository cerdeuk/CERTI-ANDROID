package org.sopt.certi.domain.repository

interface CareerRepository {
    suspend fun addCareer(
        startAt: String,
        endAt: String,
        place: String,
        name: String,
        description: String
    ): Result<Unit>
}
