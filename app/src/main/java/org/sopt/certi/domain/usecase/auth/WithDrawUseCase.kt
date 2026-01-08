package org.sopt.certi.domain.usecase.auth

import org.sopt.certi.domain.repository.AuthRepository
import javax.inject.Inject

class WithDrawUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(): Result<Unit> = authRepository.withDraw()
}
