package org.sopt.certi.core.util

import org.sopt.certi.domain.model.DateData
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

fun String.toDateModel(): DateData {
    val parts = this.split(".")
    if (parts.size != 3) return DateData()
    return DateData(
        year = parts[0].toIntOrNull(),
        month = parts[1].toIntOrNull(),
        day = parts[2].toIntOrNull()
    )
}
