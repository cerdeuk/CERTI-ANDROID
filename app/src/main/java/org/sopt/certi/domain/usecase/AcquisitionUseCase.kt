package org.sopt.certi.domain.usecase

import org.sopt.certi.domain.model.certification.CertificationData
import org.sopt.certi.domain.repository.AcquisitionRepository

class AcquisitionUseCase(
    private val acquisitionRepository: AcquisitionRepository
) {
    suspend operator fun invoke(): Result<List<CertificationData>> =
        acquisitionRepository.getAcquisitionList()

    suspend fun getAcquisitionDetail(acquisitionId: Long): Result<CertificationData> =
        acquisitionRepository.getAcquisitionDetail(acquisitionId)

    suspend fun deleteAcquisition(acquisitionId: Long): Result<Unit> =
        acquisitionRepository.deleteAcquisition(acquisitionId)
}
