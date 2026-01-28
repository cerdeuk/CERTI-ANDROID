package org.sopt.certi.core.util

fun Int.dateString() : String {
    return if (this.toString().length == 1) {
        "0$this"
    } else {
        this.toString()
    }
}