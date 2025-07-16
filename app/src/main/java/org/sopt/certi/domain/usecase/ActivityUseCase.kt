package org.sopt.certi.domain.usecase

import org.sopt.certi.domain.model.ActivityData
import org.sopt.certi.domain.repository.ActivityRepository

class ActivityUseCase(
    private val activityRepository: ActivityRepository
) {
    suspend operator fun invoke(): Result<List<ActivityData>> =
        activityRepository.getActivityList()

    suspend fun deleteActivity(activityId: Long): Result<Unit> =
        activityRepository.deleteActivity(activityId)
}
