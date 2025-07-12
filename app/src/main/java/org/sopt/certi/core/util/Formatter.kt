package org.sopt.certi.core.util

import java.text.DecimalFormat

fun Int.formatMoney(): String = DecimalFormat("#,###").format(this)
