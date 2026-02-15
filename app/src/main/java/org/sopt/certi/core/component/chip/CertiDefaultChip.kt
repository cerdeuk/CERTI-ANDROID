package org.sopt.certi.core.component.chip

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.sopt.certi.core.util.roundedBackgroundWithBorder
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun CertiChipList(
    categories: List<String>,
    modifier: Modifier = Modifier,
    spacing: Dp = screenWidthDp(6.dp),
    textStyle: TextStyle = CertiTheme.typography.caption.semibold_12,
    backgroundColor: Color = CertiTheme.colors.lightPurple,
    textColor: Color = CertiTheme.colors.mainBlue
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(spacing)
    ) {
        categories.forEach { text ->
            CertiDefaultChip(
                text = text,
                textStyle = textStyle,
                backgroundColor = backgroundColor,
                textColor = textColor
            )
        }
    }
}

@Composable
fun CertiDefaultChip(
    text: String,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = CertiTheme.typography.caption.semibold_12,
    backgroundColor: Color = CertiTheme.colors.lightPurple,
    textColor: Color = CertiTheme.colors.mainBlue
) {
    Box(
        modifier = modifier
            .roundedBackgroundWithBorder(
                cornerRadius = 12.dp,
                backgroundColor = backgroundColor
            )
            .padding(horizontal = screenWidthDp(8.dp), vertical = screenHeightDp(4.dp))
    ) {
        Text(
            text = text,
            style = textStyle,
            color = textColor,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CertiChipSetPreview() {
    CERTITheme {
        CertiChipList(categories = listOf("컴퓨터공학", "시각디자인", "경영"))
    }
}
