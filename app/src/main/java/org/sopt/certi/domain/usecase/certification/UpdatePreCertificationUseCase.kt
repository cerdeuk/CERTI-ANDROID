package org.sopt.certi.domain.usecase.certification

import org.sopt.certi.domain.repository.HomeRepository
import javax.inject.Inject

class UpdatePreCertificationUseCase @Inject constructor(
    private val homeRepository: HomeRepository
) {
    suspend operator fun invoke(certificationId: Long, testDate: String, city: String, state: String): Result<Unit> =
        homeRepository.updatePreCertification(certificationId, testDate, city, state)
}
