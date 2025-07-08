package org.sopt.certi.presentation.ui.certrecommend.component.chip

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.certi.R
import org.sopt.certi.core.util.pressedClickable
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun ReselectInterestedChip(
    chipOnClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var isPressed by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .background(color = if (isPressed) CertiTheme.colors.lightBlue else CertiTheme.colors.white, shape = RoundedCornerShape(24.dp))
            .clip(RoundedCornerShape(24.dp))
            .border(width = 1.dp, color = CertiTheme.colors.mainBlue, shape = RoundedCornerShape(24.dp))
            .pressedClickable(
                changePressed = {
                    isPressed = it
                },
                onClick = chipOnClick
            )
    ) {
        Text(
            text = stringResource(R.string.reselect_interested_chip_text),
            style = CertiTheme.typography.caption.semibold_12,
            color = CertiTheme.colors.mainBlue,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
        )
    }
}

@Preview
@Composable
fun ReselectInterestedChipPreview() {
    ReselectInterestedChip(
        chipOnClick = {}
    )
}
