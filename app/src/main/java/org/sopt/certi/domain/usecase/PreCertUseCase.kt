package org.sopt.certi.domain.usecase

import org.sopt.certi.domain.model.CertificationData
import org.sopt.certi.domain.repository.HomeRepository
import javax.inject.Inject

class PreCertUseCase @Inject constructor(
    private val homeRepository: HomeRepository
) {
    suspend operator fun invoke(): Result<List<CertificationData>> {
        return homeRepository.getPreCertificationList()
    }
}
