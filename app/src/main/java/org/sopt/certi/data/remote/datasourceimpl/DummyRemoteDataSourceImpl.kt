package org.sopt.certi.data.remote.datasourceimpl

import javax.inject.Inject
import org.sopt.certi.data.remote.datasource.DummyRemoteDataSource
import org.sopt.certi.data.remote.dto.DummyResponseDto
import org.sopt.certi.data.remote.service.DummyService

class DummyRemoteDataSourceImpl @Inject constructor(
    private val service: DummyService
) : DummyRemoteDataSource {
    override suspend fun dummy(): DummyResponseDto =
        service.dummy()
}
