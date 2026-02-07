package org.sopt.certi.domain.usecase.certification

import org.sopt.certi.domain.repository.HomeRepository
import javax.inject.Inject

class DeletePreCertificationUseCase @Inject constructor(
    private val homeRepository: HomeRepository
) {
    suspend operator fun invoke(preCertId: Long) = homeRepository.deletePreCertification(preCertId)
}
