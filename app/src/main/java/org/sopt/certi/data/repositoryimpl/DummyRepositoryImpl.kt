package org.sopt.certi.data.repositoryimpl

import org.sopt.certi.data.mapper.todomain.toDomain
import org.sopt.certi.data.remote.datasource.DummyRemoteDataSource
import org.sopt.certi.domain.model.DummyData
import org.sopt.certi.domain.repository.DummyRepository
import javax.inject.Inject

class DummyRepositoryImpl @Inject constructor(
    private val dummyRemoteDataSource: DummyRemoteDataSource
) : DummyRepository {
    override suspend fun dummy(): Result<DummyData> = runCatching {
        dummyRemoteDataSource.dummy().toDomain()
    }
}
