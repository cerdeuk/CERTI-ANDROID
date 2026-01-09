package org.sopt.certi.domain.usecase.certification

import org.sopt.certi.domain.model.certification.CertificationData
import org.sopt.certi.domain.repository.CertRepository

class Top3JobCertListUseCase(
    private val certRepository: CertRepository
) {
    suspend operator fun invoke(): Result<List<CertificationData>> =
        certRepository.getTop3JobCertList()
}