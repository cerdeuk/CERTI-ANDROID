package org.sopt.certi.domain.usecase.certification

import org.sopt.certi.domain.model.certification.CertificationData
import org.sopt.certi.domain.repository.CertRepository

class SearchCertListUseCase(
    private val certRepository: CertRepository
) {
    suspend operator fun invoke(keyword: String): Result<List<CertificationData>> =
        certRepository.searchCertList(keyword)
}
