package org.sopt.certi.presentation.ui.setting.component

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.sopt.certi.core.util.noRippleClickable
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun CustomSwitch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    width: Dp = 44.dp,
    height: Dp = 24.dp,
    thumbSize: Dp = 20.dp,
    checkedTrackColor: Color = CertiTheme.colors.purpleBlue,
    uncheckedTrackColor: Color = CertiTheme.colors.gray300
) {
    val alignmentBias by animateFloatAsState(targetValue = if (checked) 1f else -1f)

    Box(
        modifier = Modifier
            .size(width = width, height = height)
            .clip(RoundedCornerShape(height))
            .background(if (checked) checkedTrackColor else uncheckedTrackColor)
            .noRippleClickable { onCheckedChange(!checked) },
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .padding(2.dp)
                .size(thumbSize)
                .align(BiasAlignment(horizontalBias = alignmentBias, verticalBias = 0f))
                .background(Color.White, CircleShape)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CustomSwitchPreview() {
    var checked by remember { mutableStateOf(false) }

    CERTITheme {
        CustomSwitch(
            checked = checked,
            onCheckedChange = { checked = it }
        )
    }
}
