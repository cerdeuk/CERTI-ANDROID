package org.sopt.certi.domain.usecase.acquisition

import org.sopt.certi.domain.repository.AcquisitionRepository

class DeleteAcquisitionUseCase(
    private val acquisitionRepository: AcquisitionRepository
) {
    suspend operator fun invoke(acquisitionId: Long): Result<Unit> =
        acquisitionRepository.deleteAcquisition(acquisitionId)
}
