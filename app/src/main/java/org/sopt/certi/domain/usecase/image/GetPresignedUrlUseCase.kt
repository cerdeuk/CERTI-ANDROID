package org.sopt.certi.domain.usecase.image

import org.sopt.certi.domain.model.image.PresignedData
import org.sopt.certi.domain.repository.UserRepository
import javax.inject.Inject

class GetPresignedUrlUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(): Result<PresignedData> = userRepository.getPresignedUrl()
}
