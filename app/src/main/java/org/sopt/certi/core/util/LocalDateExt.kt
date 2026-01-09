package org.sopt.certi.core.util

import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun LocalDate.formatToDotDate(): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy. MM. dd")
    return this.format(formatter)
}
