package org.sopt.certi.domain.usecase.acquisition

import org.sopt.certi.domain.repository.AcquisitionRepository
import javax.inject.Inject

class AcquiredCertUseCase @Inject constructor(
    private val acquisitionRepository: AcquisitionRepository
) {
    suspend operator fun invoke(certificationId: Long) = acquisitionRepository.acquiredCert(certificationId)
}