package org.sopt.certi.domain.usecase.auth

import org.sopt.certi.domain.repository.AuthRepository

class SearchUnivUseCase(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(keyword: String): Result<List<String>> =
        authRepository.searchUniv(keyword = keyword)
}
