package org.sopt.certi.domain.usecase.user

import org.sopt.certi.domain.repository.UserRepository
import javax.inject.Inject

class ModifyInterestedJobListUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(jobNameList: List<String>): Result<Unit> = userRepository.modifyInterestedJobList(jobNameList)
}
