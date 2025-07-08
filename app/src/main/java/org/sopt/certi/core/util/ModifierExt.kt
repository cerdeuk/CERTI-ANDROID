package org.sopt.certi.core.util

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

inline fun Modifier.noRippleClickable(
    crossinline onClick: () -> Unit
): Modifier = composed {
    clickable(
        indication = null,
        interactionSource = remember { MutableInteractionSource() }
    ) {
        onClick()
    }
}

fun Modifier.showIf(condition: Boolean): Modifier {
    return if (condition) this else Modifier.size(0.dp)
}

fun Modifier.roundedBackgroundWithBorder(
    cornerRadius: Dp,
    backgroundColor: Color,
    borderColor: Color = Color.Transparent,
    borderWidth: Dp = 0.dp
): Modifier {
    return this
        .background(backgroundColor, shape = RoundedCornerShape(cornerRadius))
        .border(
            width = borderWidth,
            color = borderColor,
            shape = RoundedCornerShape(cornerRadius)
        )
}

inline fun Modifier.pressedClickable(
    crossinline changePressed: (Boolean) -> Unit,
    crossinline onClick: () -> Unit,
    throttleDelay: Long = 300L
): Modifier = composed {
    // Compose 뷰에 pressed 상태 콜백과 클릭 throttle 기능을 추가해,
    // 터치 시작/종료 시 changePressed를 호출하고 일정 간격 이상일 때만 onClick을 실행하도록 한다

    var lastClickTime by remember { mutableLongStateOf(0L) }

    pointerInput(Unit) {
        detectTapGestures(
            onPress = {
                changePressed(true)
                tryAwaitRelease()
                changePressed(false)
            },
            onTap = {
                val currentTime = System.currentTimeMillis()
                if (currentTime - lastClickTime >= throttleDelay) {
                    lastClickTime = currentTime
                    onClick()
                }
            }
        )
    }
}

@Composable
fun Modifier.heightForScreenPercentage(percentage: Float): Modifier {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    return this.height(screenHeight * percentage)
}

@Composable
fun Modifier.widthForScreenPercentage(percentage: Float): Modifier {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    return this.width(screenWidth * percentage)
}
