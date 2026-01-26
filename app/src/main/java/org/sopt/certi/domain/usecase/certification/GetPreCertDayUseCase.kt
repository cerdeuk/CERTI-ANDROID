package org.sopt.certi.domain.usecase.certification

import org.sopt.certi.domain.repository.HomeRepository
import javax.inject.Inject

class GetPreCertDayUseCase @Inject constructor(
    private val homeRepository: HomeRepository
) {
    suspend operator fun invoke(date: String) = homeRepository.getPreCertDay(date)
}
