package org.sopt.certi.domain.usecase.career

import org.sopt.certi.domain.repository.CareerRepository

class DeleteCareerUseCase(
    private val careerRepository: CareerRepository
) {
    suspend operator fun invoke(careerId: Long): Result<Unit> =
        careerRepository.deleteCareer(careerId)
}
