package org.sopt.certi.domain.usecase.career

import org.sopt.certi.domain.model.ActivityData
import org.sopt.certi.domain.repository.CareerRepository

class GetCareerListUseCase(
    private val careerRepository: CareerRepository
) {
    suspend operator fun invoke(): Result<List<ActivityData>> =
        careerRepository.getCareerList()
}
