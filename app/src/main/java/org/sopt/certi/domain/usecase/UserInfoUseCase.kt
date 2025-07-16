package org.sopt.certi.domain.usecase

import org.sopt.certi.domain.model.user.UserInfoData
import org.sopt.certi.domain.repository.HomeRepository
import javax.inject.Inject

class UserInfoUseCase @Inject constructor(
private val homeRepository: HomeRepository
) {
    suspend operator fun invoke(): Result<UserInfoData> {
        return homeRepository.getUserInfo()
    }
}