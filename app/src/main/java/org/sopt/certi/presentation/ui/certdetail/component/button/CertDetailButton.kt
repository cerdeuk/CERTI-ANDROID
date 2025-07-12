package org.sopt.certi.presentation.ui.certdetail.component.button

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.certi.core.util.pressedClickable
import org.sopt.certi.presentation.type.AcquireButtonType
import org.sopt.certi.ui.theme.CertiTheme
import org.sopt.certi.ui.theme.White

@Composable
fun AcquireButton(
    acquireButtonType: AcquireButtonType,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var isPressed by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .background(
                color = if (isPressed) acquireButtonType.pressedBackgroundColor else acquireButtonType.backgroundColor,
                shape = RoundedCornerShape(12.dp)
            )
            .clip(RoundedCornerShape(12.dp))
            .border(
                width = if (acquireButtonType.borderAvailable) 1.dp else 0.dp,
                color = if (isPressed) CertiTheme.colors.skyBlue else CertiTheme.colors.lightBlue,
                shape = RoundedCornerShape(12.dp)
            )
            .pressedClickable(
                changePressed = {
                    isPressed = it
                },
                onClick = onClick
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(acquireButtonType.buttonText),
            style = CertiTheme.typography.body.semibold_16,
            color = acquireButtonType.buttonTextColor,
            modifier = Modifier.padding(vertical = 14.dp)
        )
    }
}

@Preview
@Composable
fun AcquireButtonPreview() {
    Column(
        modifier = Modifier.background(color = White),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        AcquireButton(acquireButtonType = AcquireButtonType.FINISH, onClick = {}, modifier = Modifier.fillMaxWidth())
        AcquireButton(acquireButtonType = AcquireButtonType.EXPECTED, onClick = {}, modifier = Modifier.fillMaxWidth())
    }
}
