package org.sopt.certi.domain.usecase

import org.sopt.certi.domain.repository.HomeRepository

class ToggleFavoriteUseCase(
    private val homeRepository: HomeRepository
) {
    suspend operator fun invoke(certificationId: Long): Result<Unit> {
        return homeRepository.toggleFavorite(certificationId)
    }
}
