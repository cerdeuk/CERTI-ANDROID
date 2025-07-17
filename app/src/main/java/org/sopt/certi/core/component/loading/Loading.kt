package org.sopt.certi.core.component.loading

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.sopt.certi.R
import org.sopt.certi.core.util.heightForScreenPercentage
import org.sopt.certi.core.util.roundedBackgroundWithBorder
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.core.util.widthForScreenPercentage
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun LoadingLayout(
    userName: String
) {
    val infiniteTransition = rememberInfiniteTransition(label = "infinite_scroll")
    val animatedOffset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 4f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 8000,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Restart
        ),
        label = "scroll_offset"
    )

    val bounceAnim by rememberInfiniteTransition().animateFloat(
        initialValue = 0f,
        targetValue = 10f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 500,
                easing = FastOutLinearInEasing
            ),
            repeatMode = RepeatMode.Reverse
        )
    )

    val totalContentWidth = screenWidthDp(166.dp)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(CertiTheme.colors.white),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    modifier = Modifier
                        .widthForScreenPercentage(166.dp)
                        .clipToBounds()
                        .offset(x = totalContentWidth)
                        .offset(x = -totalContentWidth * animatedOffset)
                ) {
                    ColorBoxRow()
                }

                Row(
                    modifier = Modifier
                        .widthForScreenPercentage(166.dp)
                        .clipToBounds()
                        .offset(x = totalContentWidth * 2 + screenWidthDp(24.dp))
                        .offset(x = -totalContentWidth * animatedOffset)
                ) {
                    ColorBoxRow()
                }

                Row(
                    modifier = Modifier
                        .widthForScreenPercentage(166.dp)
                        .clipToBounds()
                        .offset(x = totalContentWidth * 3 + screenWidthDp(48.dp))
                        .offset(x = -totalContentWidth * animatedOffset)
                ) {
                    ColorBoxRow()
                }

                Row(
                    modifier = Modifier
                        .widthForScreenPercentage(166.dp)
                        .clipToBounds()
                        .offset(x = totalContentWidth * 4 + screenWidthDp(72.dp))
                        .offset(x = -totalContentWidth * animatedOffset)
                ) {
                    ColorBoxRow()
                }

                Image(
                    painter = painterResource(R.drawable.img_loading),
                    contentDescription = null,
                    modifier = Modifier
                        .widthForScreenPercentage(118.dp)
                        .wrapContentHeight()
                        .offset(y = bounceAnim.dp)
                )
            }

            Spacer(Modifier.heightForScreenPercentage(20.dp))

            Text(
                text = stringResource(R.string.loading_title, userName),
                style = CertiTheme.typography.body.semibold_16,
                color = CertiTheme.colors.gray600,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun ColorBoxRow() {
    ColorBox(CertiTheme.colors.purpleBlue)
    Spacer(Modifier.widthForScreenPercentage(24.dp))
    ColorBox(CertiTheme.colors.skyBlue)
    Spacer(Modifier.widthForScreenPercentage(24.dp))
    ColorBox(CertiTheme.colors.subYellow)
    Spacer(Modifier.widthForScreenPercentage(24.dp))
    ColorBox(CertiTheme.colors.lightBlue)
    Spacer(Modifier.widthForScreenPercentage(24.dp))
    ColorBox(CertiTheme.colors.lightPurple)
}

@Composable
private fun ColorBox(backgroundColor: Color) {
    Box(
        modifier = Modifier
            .widthForScreenPercentage(14.dp)
            .heightForScreenPercentage(20.dp)
            .roundedBackgroundWithBorder(
                cornerRadius = 2.dp,
                backgroundColor = backgroundColor
            )
    )
}
