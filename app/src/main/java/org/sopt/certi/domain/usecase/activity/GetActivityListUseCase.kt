package org.sopt.certi.domain.usecase.activity

import org.sopt.certi.domain.model.ActivityData
import org.sopt.certi.domain.repository.ActivityRepository

class GetActivityListUseCase(
    private val activityRepository: ActivityRepository
) {
    suspend operator fun invoke(): Result<List<ActivityData>> =
        activityRepository.getActivityList()
}
