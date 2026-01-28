package org.sopt.certi.domain.repository

interface S3Repository {
    suspend fun uploadImage(presignedUrl: String, imageUri: String): Result<Unit>
}
