package org.sopt.certi.core.component.button

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.certi.core.util.pressedClickable
import org.sopt.certi.ui.theme.Gray100
import org.sopt.certi.ui.theme.Gray300
import org.sopt.certi.ui.theme.Gray400
import org.sopt.certi.ui.theme.LocalCertiTypographyProvider
import org.sopt.certi.ui.theme.MainBlue
import org.sopt.certi.ui.theme.PurpleBlue
import org.sopt.certi.ui.theme.White

@Composable
fun CertiBasicButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    buttonText: String = "",
    onClick: () -> Unit = {},
) {
    val typoProvider = LocalCertiTypographyProvider.current

    var isPressed by remember { mutableStateOf(false) }
    val currentEnabled by rememberUpdatedState(enabled)

    Box(
        modifier = modifier
            .height(56.dp)
            .background(
                color = if(!enabled) Gray100 else {
                    if (isPressed) MainBlue else PurpleBlue
                },
                shape = RoundedCornerShape(12.dp)
            )
            .clip(RoundedCornerShape(12.dp))
            .pressedClickable(
                changePressed = {
                    isPressed = it
                },
                onClick = {
                    if(currentEnabled) {
                        onClick.invoke()
                    }
                }
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = buttonText,
            style = typoProvider.body.semibold_16,
            color = if(enabled) White else Gray400
        )
    }
}

@Preview
@Composable
fun BasicButtonPreview() {
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        // 다음 버튼
        CertiBasicButton(
            modifier = Modifier.fillMaxWidth(),
            enabled = false,
            buttonText = "다음"
        )

        CertiBasicButton(
            modifier = Modifier.fillMaxWidth(),
            enabled = true,
            buttonText = "다음"
        )

        // 적용하기 버튼
        CertiBasicButton(
            modifier = Modifier.fillMaxWidth(),
            enabled = false,
            buttonText = "적용하기"
        )

        CertiBasicButton(
            modifier = Modifier.fillMaxWidth(),
            enabled = true,
            buttonText = "적용하기"
        )

        // 추가하기 버튼
        CertiBasicButton(
            modifier = Modifier.fillMaxWidth(),
            enabled = false,
            buttonText = "추가하기"
        )

        CertiBasicButton(
            modifier = Modifier.fillMaxWidth(),
            enabled = true,
            buttonText = "추가하기"
        )
    }
}