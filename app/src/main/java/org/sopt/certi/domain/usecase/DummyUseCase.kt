package org.sopt.certi.domain.usecase

import javax.inject.Inject
import org.sopt.certi.domain.model.DummyData
import org.sopt.certi.domain.repository.DummyRepository

class DummyUseCase @Inject constructor(
    private val dummyRepository: DummyRepository
) {
    suspend operator fun invoke(): Result<DummyData> = dummyRepository.dummy()
}
