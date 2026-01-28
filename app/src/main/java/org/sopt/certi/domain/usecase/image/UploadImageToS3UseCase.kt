package org.sopt.certi.domain.usecase.image

import org.sopt.certi.domain.repository.S3Repository
import javax.inject.Inject

class UploadImageToS3UseCase @Inject constructor(
    private val s3Repository: S3Repository
) {
    suspend operator fun invoke(presignedUrl: String, imageUri: String): Result<Unit> {
        return s3Repository.uploadImage(presignedUrl, imageUri)
    }
}
