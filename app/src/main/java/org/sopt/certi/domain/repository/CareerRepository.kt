package org.sopt.certi.domain.repository

import org.sopt.certi.domain.model.ActivityData

interface CareerRepository {
    suspend fun addCareer(
        startAt: String,
        endAt: String,
        place: String,
        name: String,
        description: String
    ): Result<Unit>
    suspend fun getCareerList(): Result<List<ActivityData>>
    suspend fun deleteCareer(careerId: Long): Result<Unit>
}
