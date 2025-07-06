package org.sopt.certi.core.component.chip

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun CertiChipSet(
    chipTexts: List<String>,
    modifier: Modifier = Modifier,
    spacing: Dp = 4.dp  // 기본값
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(spacing)
    ) {
        chipTexts.forEach { text ->
            CertiDefaultChip(text = text)
        }
    }
}

@Composable
private fun CertiDefaultChip(
    text: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(CertiTheme.colors.lightPurple)
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(
            text = text,
            style = CertiTheme.typography.caption.semibold_12,
            color = CertiTheme.colors.mainBlue
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CertiChipSetPreview() {
    CERTITheme {
        CertiChipSet(chipTexts = listOf("컴퓨터공학", "시각디자인", "경영"))
    }
}
