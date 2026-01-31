package org.sopt.certi.domain.usecase.career

import org.sopt.certi.domain.repository.CareerRepository

class EditCareerUseCase(
    private val careerRepository: CareerRepository
) {
    suspend operator fun invoke(
        careerId: Long,
        startAt: String,
        endAt: String,
        place: String,
        name: String,
        description: String
    ): Result<Unit> = careerRepository.editCareer(careerId, startAt, endAt, place, name, description)
}
