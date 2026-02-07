package org.sopt.certi.domain.usecase.acquisition

import org.sopt.certi.domain.repository.AcquisitionRepository
import javax.inject.Inject

class UpdateAcquisitionUseCase @Inject constructor(
    private val acquisitionRepository: AcquisitionRepository
) {
    suspend operator fun invoke(acquisitionId: Long, acquisitionDate: String, grade: String): Result<Unit> =
        acquisitionRepository.updateAcquisition(acquisitionId, acquisitionDate, grade)
}
