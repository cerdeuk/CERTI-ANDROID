package org.sopt.certi.presentation.model

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.sopt.certi.R

data class ToastConfig(
    val iconId: Int = R.drawable.ic_progresscheck_48,
    val titleMessage: String,
    val contentMessage: String,
    val yOffset: Dp = 100.dp,
    val endToastAction: () -> Unit
)
