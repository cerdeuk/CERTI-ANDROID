package org.sopt.certi.domain.usecase

import org.sopt.certi.domain.repository.ActivityRepository

class AddActivityUseCase(
    private val activityRepository: ActivityRepository
){
    suspend operator fun invoke(
        startAt: String,
        endAt: String,
        place: String,
        name: String,
        description: String
    ): Result<Unit> = activityRepository.addActivity(startAt, endAt, place, name, description)
}