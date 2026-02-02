package org.sopt.certi.data.remote.datasource

import android.net.Uri

interface S3DataSource {
    suspend fun uploadImageToS3(presignedUrl: String, imageUri: Uri)
}
