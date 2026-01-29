package org.sopt.certi.core.util

import org.sopt.certi.core.state.UiState

fun <T> UiState<T>.getSuccessDataOrNull(): T? {
    return (this as? UiState.Success)?.data
}
