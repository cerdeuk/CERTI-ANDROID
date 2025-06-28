package org.sopt.certi.data.remote.service

import org.sopt.certi.data.remote.dto.DummyResponseDto
import retrofit2.http.GET

interface DummyService {
    @GET("/API")
    suspend fun dummy(): DummyResponseDto
}