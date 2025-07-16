package org.sopt.certi.domain.usecase

import org.sopt.certi.domain.model.CertificationData
import org.sopt.certi.domain.repository.AcquisitionRepository

class AcquisitionUseCase(
    private val acquisitionRepository: AcquisitionRepository
) {
    suspend operator fun invoke(): Result<List<CertificationData>> =
        acquisitionRepository.getAcquisitionList()
}
