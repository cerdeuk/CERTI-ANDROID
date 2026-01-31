package org.sopt.certi.core.util

fun Int.dateString(): String {
    return this.toString().padStart(2, '0')
}
