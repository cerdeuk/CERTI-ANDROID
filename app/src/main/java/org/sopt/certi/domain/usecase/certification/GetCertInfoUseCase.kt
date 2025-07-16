package org.sopt.certi.domain.usecase.certification

import org.sopt.certi.domain.repository.CertRepository
import javax.inject.Inject

class GetCertInfoUseCase @Inject constructor(
    private val certRepository: CertRepository
) {
    suspend operator fun invoke(certificationId: Long) = certRepository.getCertInfo(certificationId)
}