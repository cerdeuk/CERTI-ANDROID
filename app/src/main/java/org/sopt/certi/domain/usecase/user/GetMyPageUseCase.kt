package org.sopt.certi.domain.usecase.user

import org.sopt.certi.domain.model.user.MyPageInfo
import org.sopt.certi.domain.repository.UserRepository
import javax.inject.Inject

class GetMyPageUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(): Result<MyPageInfo> =
        userRepository.getMyPageInfo()
}
