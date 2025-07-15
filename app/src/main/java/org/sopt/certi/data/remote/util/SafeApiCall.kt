package org.sopt.certi.data.remote.util

inline fun <T> safeApiCall(block: () -> T): Result<T> {
    return runCatching { block() }
        .fold(
            onSuccess = { Result.success(it) },
            onFailure = { Result.failure(HttpResponseHandler.parseHttpException(it)) }
        )
}
