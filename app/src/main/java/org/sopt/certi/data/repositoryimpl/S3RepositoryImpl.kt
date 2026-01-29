package org.sopt.certi.data.repositoryimpl

import androidx.core.net.toUri
import org.sopt.certi.data.remote.datasource.S3DataSource
import org.sopt.certi.domain.repository.S3Repository
import javax.inject.Inject

class S3RepositoryImpl @Inject constructor(
    private val s3DataSource: S3DataSource
) : S3Repository {
    override suspend fun uploadImage(presignedUrl: String, imageUri: String): Result<Unit> {
        return runCatching {
            s3DataSource.uploadImageToS3(presignedUrl, imageUri.toUri())
        }
    }
}
