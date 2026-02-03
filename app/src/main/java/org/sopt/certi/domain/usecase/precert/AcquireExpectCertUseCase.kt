package org.sopt.certi.domain.usecase.precert

import org.sopt.certi.domain.repository.PreCertRepository
import javax.inject.Inject

class AcquireExpectCertUseCase @Inject constructor(
    private val preCertRepository: PreCertRepository
) {
    suspend operator fun invoke(
        certificationId: Long,
        city: String? = null,
        state: String? = null,
        testDate: String? = null
    ): Result<Boolean> {
        return preCertRepository.acquireExpectCert(certificationId, city, state, testDate)
    }
}
