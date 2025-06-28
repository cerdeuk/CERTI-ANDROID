package org.sopt.certi.data.remote.datasource

import org.sopt.certi.data.remote.dto.DummyResponseDto

interface DummyRemoteDataSource {
    suspend fun dummy(): DummyResponseDto
}