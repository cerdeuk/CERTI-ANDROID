package org.sopt.certi.core.util

import java.time.LocalDate
import java.time.format.DateTimeFormatter

object DateFormatters {
    val dotDate: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
}

fun String.toLocalDateOrMin(): LocalDate =
    runCatching { LocalDate.parse(this, DateFormatters.dotDate) }
        .getOrElse {
            it.printStackTrace()
            LocalDate.MIN
        }

fun String.parseDateToYearMonthDay(): Triple<String, String, String> {
    if (this.isBlank()) return Triple("", "", "")
    return runCatching {
        val parts = this.split(".")
        Triple(parts[0], parts[1], parts[2])
    }.getOrElse {
        Triple("", "", "")
    }
}
