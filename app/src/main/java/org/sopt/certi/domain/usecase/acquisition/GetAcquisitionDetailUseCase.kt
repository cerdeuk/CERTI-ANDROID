package org.sopt.certi.domain.usecase.acquisition

import org.sopt.certi.domain.model.certification.CertificationData
import org.sopt.certi.domain.repository.AcquisitionRepository

class GetAcquisitionDetailUseCase(
    private val acquisitionRepository: AcquisitionRepository
) {
    suspend operator fun invoke(acquisitionId: Long): Result<CertificationData> =
        acquisitionRepository.getAcquisitionDetail(acquisitionId)
}
