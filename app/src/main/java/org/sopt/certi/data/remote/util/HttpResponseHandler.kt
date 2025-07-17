package org.sopt.certi.data.remote.util

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.HttpResponseException
import kotlinx.serialization.json.Json
import org.sopt.certi.data.remote.dto.base.ApiResponse
import org.sopt.certi.data.remote.dto.base.NullableApiResponse
import retrofit2.HttpException
import timber.log.Timber

object HttpResponseHandler {
    private val json = Json { ignoreUnknownKeys = true }

    fun <T> ApiResponse<T>.handleApiResponse(): Result<T> =
        if (this.status in 200..299) {
            Result.success(this.data)
        } else {
            Result.failure(Exception("Unknown error : ${this.message}"))
        }

    fun <T> NullableApiResponse<T>.handleNullableApiResponse(): Result<T?> =
        if (this.status in 200..299) {
            Result.success(this.data)
        } else {
            Result.failure(Exception("Unknown error : ${this.message}"))
        }

    fun parseHttpException(e: Throwable): Exception {
        return if (e is HttpException) {
            try {
                val errorBody = e.response()?.errorBody()?.string()
                val errorResponse = errorBody?.let {
                    json.decodeFromString<NullableApiResponse<Unit>>(it)
                } ?: NullableApiResponse(status = e.code(), code = e.message(), message = e.message(), data = null)

                when (errorResponse.status) {
                    400 -> HttpResponseException(400, "Bad Request : ${errorResponse.message}")
                    401 -> HttpResponseException(401, "Unauthorized : ${errorResponse.message}")
                    403 -> HttpResponseException(403, "Forbidden : ${errorResponse.message}")
                    404 -> HttpResponseException(404, "Not Found : ${errorResponse.message}")
                    409 -> HttpResponseException(409, "Conflict : ${errorResponse.message}")
                    500 -> HttpResponseException(500, "Internal Server Error : ${errorResponse.message}")
                    else -> HttpResponseException(errorResponse.status, "Unknown error : ${errorResponse.message}")
                }
            } catch (ex: Exception) {
                Exception("Failed to parse error response.")
            }
        } else {
            Exception(e.message)
        }
    }
}
