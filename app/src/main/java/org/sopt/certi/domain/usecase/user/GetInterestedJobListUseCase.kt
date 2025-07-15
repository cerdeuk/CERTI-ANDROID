package org.sopt.certi.domain.usecase.user

import org.sopt.certi.domain.model.InterestedJobListData
import org.sopt.certi.domain.repository.UserRepository
import javax.inject.Inject

class GetInterestedJobListUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(): Result<InterestedJobListData> = userRepository.getInterestedJobList()
}