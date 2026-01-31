package org.sopt.certi.domain.usecase.certification

import org.sopt.certi.domain.repository.HomeRepository
import javax.inject.Inject

class GetPreCertMonthUseCase @Inject constructor(
    private val homeRepository: HomeRepository
) {
    suspend operator fun invoke(year: Int, month: Int) = homeRepository.getPreCertMonth(year, month)
}
