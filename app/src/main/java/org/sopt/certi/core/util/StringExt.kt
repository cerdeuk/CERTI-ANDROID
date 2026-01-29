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

fun String.toLocalDateOrNull(): LocalDate? =
    runCatching { LocalDate.parse(this, DateFormatters.dotDate) }
        .getOrElse { null }

fun String.dateString(): String {
    return this.padStart(2, '0')
}
