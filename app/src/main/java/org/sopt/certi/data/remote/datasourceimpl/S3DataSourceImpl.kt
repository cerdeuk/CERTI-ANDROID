package org.sopt.certi.data.remote.datasourceimpl

import android.content.Context
import android.net.Uri
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.sopt.certi.data.remote.datasource.S3DataSource
import org.sopt.certi.data.remote.service.S3Service
import javax.inject.Inject

class S3DataSourceImpl @Inject constructor(
    private val s3Service: S3Service,
    @ApplicationContext private val context: Context
) : S3DataSource {
    override suspend fun uploadImageToS3(presignedUrl: String, imageUri: Uri) {
        val inputStream = context.contentResolver.openInputStream(imageUri)
        val byteArray = inputStream?.readBytes() ?: throw IllegalStateException("이미지를 읽을 수 없습니다.")
        inputStream.close()

        val requestBody = byteArray.toRequestBody("image/*".toMediaTypeOrNull())

        val response = s3Service.uploadImage(presignedUrl, requestBody)

        if (!response.isSuccessful) {
            throw Exception("S3 업로드 실패: ${response.code()}")
        }
    }
}
