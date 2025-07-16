package org.sopt.certi.domain.repository

import org.sopt.certi.domain.model.ActivityData

interface ActivityRepository {
    suspend fun getActivityList(): Result<List<ActivityData>>
    suspend fun deleteActivity(activityId: Long): Result<Unit>
}
