package org.sopt.certi.domain.usecase

import org.sopt.certi.domain.repository.PreCertEditRepository

class PreCertEditUseCase(
    private val preCertEditRepository: PreCertEditRepository
) {
    suspend operator fun invoke(certificationId: Long): Result<Unit> {
        return preCertEditRepository.deletePreCertification(certificationId)
    }
}
