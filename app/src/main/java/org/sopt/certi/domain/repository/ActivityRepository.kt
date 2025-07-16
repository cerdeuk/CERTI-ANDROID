package org.sopt.certi.domain.repository

import org.sopt.certi.domain.model.ActivityData

interface ActivityRepository {
    suspend fun addActivity(
        startAt: String,
        endAt: String,
        place: String,
        name: String,
        description: String
    ): Result<Unit>
    suspend fun getActivityList(): Result<List<ActivityData>>
    suspend fun deleteActivity(activityId: Long): Result<Unit>
}
