package org.sopt.certi.domain.usecase

import org.sopt.certi.domain.model.ActivityData
import org.sopt.certi.domain.repository.CareerRepository

class CareerUseCase(
    private val careerRepository: CareerRepository
) {
    suspend operator fun invoke(): Result<List<ActivityData>> =
        careerRepository.getCareerList()
}
