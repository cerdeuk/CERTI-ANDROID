package org.sopt.certi.domain.usecase

import org.sopt.certi.domain.repository.CareerRepository

class AddCareerUseCase(
    private val careerRepository: CareerRepository
) {
    suspend operator fun invoke(
        startAt: String,
        endAt: String,
        place: String,
        name: String,
        description: String
    ): Result<Unit> = careerRepository.addCareer(startAt, endAt, place, name, description)
}
