package org.sopt.certi.domain.usecase.activity

import org.sopt.certi.domain.repository.ActivityRepository

class DeleteActivityUseCase(
    private val activityRepository: ActivityRepository
) {
    suspend operator fun invoke(activityId: Long): Result<Unit> =
        activityRepository.deleteActivity(activityId)
}
