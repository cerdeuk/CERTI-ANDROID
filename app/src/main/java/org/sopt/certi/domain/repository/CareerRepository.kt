package org.sopt.certi.domain.repository

import org.sopt.certi.domain.model.ActivityData

interface CareerRepository {
    suspend fun getCareerList(): Result<List<ActivityData>>
    suspend fun deleteCareer(careerId: Long): Result<Unit>
}
