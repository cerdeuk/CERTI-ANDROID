package org.sopt.certi.domain.usecase

import org.sopt.certi.domain.model.certification.CertificationData
import org.sopt.certi.domain.repository.HomeRepository
import javax.inject.Inject

class HomeRecommendUseCase @Inject constructor(
    private val homeRepository: HomeRepository
) {
    suspend operator fun invoke(): Result<List<CertificationData>> =
        homeRepository.getRecommendedCertList().map { it.take(3) }
}