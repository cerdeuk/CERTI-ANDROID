package org.sopt.certi.domain.usecase.activity

import org.sopt.certi.domain.repository.ActivityRepository

class EditActivityUseCase(
    private val activityRepository: ActivityRepository
) {
    suspend operator fun invoke(
        activityId: Long,
        startAt: String,
        endAt: String,
        place: String,
        name: String,
        description: String
    ): Result<Unit> = activityRepository.editActivity(activityId, startAt, endAt, place, name, description)
}
