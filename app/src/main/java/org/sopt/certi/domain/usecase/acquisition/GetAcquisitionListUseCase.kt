package org.sopt.certi.domain.usecase.acquisition

import org.sopt.certi.domain.model.certification.CertificationData
import org.sopt.certi.domain.repository.AcquisitionRepository

class GetAcquisitionListUseCase(
    private val acquisitionRepository: AcquisitionRepository
) {
    suspend operator fun invoke(): Result<List<CertificationData>> =
        acquisitionRepository.getAcquisitionList()
}
