package org.sopt.certi.core.component.chip

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun ResetBadge(
    text: String,
    modifier: Modifier = Modifier,
    textColor: Color = CertiTheme.colors.white,
    textStyle: TextStyle = CertiTheme.typography.caption.regular_10,
    backgroundColor: Color = CertiTheme.colors.mainBlue,
    radius: Dp = 8.dp,
    maxLines: Int = 1,
    overFlow: TextOverflow = TextOverflow.Ellipsis
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(radius))
            .background(backgroundColor)
            .padding(horizontal = screenWidthDp(4.dp), vertical = screenWidthDp(2.dp))
    ) {
        Text(
            text = text,
            color = textColor,
            style = textStyle,
            maxLines = maxLines,
            overflow = overFlow
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ResetBadgePreview() {
    CERTITheme {
        ResetBadge(
            text = "1지망"
        )
    }
}
