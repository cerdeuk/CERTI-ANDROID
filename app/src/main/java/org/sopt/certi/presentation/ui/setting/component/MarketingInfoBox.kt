package org.sopt.certi.presentation.ui.setting.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import org.sopt.certi.R
import org.sopt.certi.core.util.noRippleClickable
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.core.util.widthForScreenPercentage
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun MarketingInfoBox() {
    var showTooltip by remember { mutableStateOf(false) }
    val density = LocalDensity.current

    Box {
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_question_24),
            contentDescription = null,
            tint = CertiTheme.colors.gray300,
            modifier = Modifier.noRippleClickable { showTooltip = true }
        )

        if (showTooltip) {
            Popup(
                onDismissRequest = { showTooltip = false },
                offset = IntOffset(
                    x = with(density) { -screenWidthDp(60.dp).roundToPx() },
                    y = with(density) { screenHeightDp(28.dp).roundToPx() }
                ),
                properties = PopupProperties(focusable = true)
            ) {
                TooltipContent(
                    onClose = { showTooltip = false }
                )
            }
        }
    }
}

@Composable
fun TooltipContent(onClose: () -> Unit) {
    Column(
        modifier = Modifier
            .widthForScreenPercentage(216.dp)
            .clip(RoundedCornerShape(8.dp))
            .border(1.dp, CertiTheme.colors.gray400, RoundedCornerShape(8.dp))
            .background(color = Color.White)
            .padding(horizontal = screenWidthDp(10.dp), vertical = screenHeightDp(6.dp))
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.setting_marketing),
                style = CertiTheme.typography.caption.semibold_12,
                color = CertiTheme.colors.gray500,
                modifier = Modifier.weight(1f)
            )

            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = null,
                tint = CertiTheme.colors.gray300,
                modifier = Modifier.noRippleClickable(onClose)
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = stringResource(R.string.setting_privacy_tooltip_text),
            style = CertiTheme.typography.caption.regular_12,
            color = CertiTheme.colors.gray400
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MarketingInfoBoxPreview() {
    CERTITheme {
        MarketingInfoBox()
    }
}
