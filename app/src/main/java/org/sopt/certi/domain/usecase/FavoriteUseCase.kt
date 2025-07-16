package org.sopt.certi.domain.usecase

import org.sopt.certi.domain.model.certification.CertificationData
import org.sopt.certi.domain.repository.HomeRepository
import javax.inject.Inject

class FavoriteUseCase @Inject constructor(
    private val homeRepository: HomeRepository
) {
    suspend operator fun invoke(): Result<List<CertificationData>> {
        return homeRepository.getFavoriteList()
    }
}
