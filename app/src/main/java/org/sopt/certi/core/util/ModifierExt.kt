package org.sopt.certi.core.util

import android.graphics.BlurMaskFilter
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
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

private val figmaScreenWidth = 360.dp
private val figmaScreenHeight = 780.dp

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

fun Modifier.dropShadow(
    shape: Shape,
    color: Color = Color.Black.copy(0.25f),
    blur: Dp = 4.dp,
    offsetY: Dp = 4.dp,
    offsetX: Dp = 0.dp,
    spread: Dp = 0.dp
) = this.drawBehind {
    val shadowSize = Size(size.width + spread.toPx(), size.height + spread.toPx())
    val shadowOutline = shape.createOutline(shadowSize, layoutDirection, this)

    val paint = Paint().apply {
        this.color = color
    }

    if (blur.toPx() > 0) {
        paint.asFrameworkPaint().apply {
            maskFilter = BlurMaskFilter(blur.toPx(), BlurMaskFilter.Blur.NORMAL)
        }
    }

    drawIntoCanvas { canvas ->
        canvas.save()
        canvas.translate(offsetX.toPx(), offsetY.toPx())
        canvas.drawOutline(shadowOutline, paint)
        canvas.restore()
    }
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
fun Modifier.heightForScreenPercentage(height: Dp): Modifier {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val ratio = screenHeight / figmaScreenHeight
    return this.height(height * ratio)
}

@Composable
fun Modifier.widthForScreenPercentage(width: Dp): Modifier {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val ratio = screenWidth / figmaScreenWidth
    return this.width(width * ratio)
}

fun Modifier.bottomBorder(
    strokeWidth: Dp,
    color: Color
): Modifier =
    this.drawBehind {
        val strokeWidthPx = strokeWidth.toPx()
        val width = size.width
        val height = size.height - strokeWidthPx / 2

        drawLine(
            color = color,
            start = Offset(x = 0f, y = height),
            end = Offset(x = width, y = height),
            strokeWidth = strokeWidthPx
        )
    }
