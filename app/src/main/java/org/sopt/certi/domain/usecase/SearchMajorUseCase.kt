package org.sopt.certi.domain.usecase

import org.sopt.certi.domain.repository.AuthRepository

class SearchMajorUseCase(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(keyword: String): Result<List<String>> =
        authRepository.searchMajor(keyword = keyword)
}
