package org.sopt.certi.domain.usecase.certification

import org.sopt.certi.domain.repository.CertRepository
import javax.inject.Inject

class GetCategoryCertListUseCase @Inject constructor(
    private val certRepository: CertRepository
) {
    suspend operator fun invoke(isFavorite: Boolean, jobs: String) =
        certRepository.getCategoryCertList(isFavorite, jobs)
}