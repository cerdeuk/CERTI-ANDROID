package org.sopt.certi.core.component.loading

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import org.sopt.certi.R
import org.sopt.certi.core.util.heightForScreenPercentage
import org.sopt.certi.core.util.roundedBackgroundWithBorder
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.core.util.widthForScreenPercentage
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun LoadingLayout() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.lottie_loading))
    var isPlaying by remember { mutableStateOf(true) }
    val progress by animateLottieCompositionAsState(
        composition = composition,
        isPlaying = isPlaying,
        iterations = LottieConstants.IterateForever
    )

    val infiniteTransition = rememberInfiniteTransition(label = "infinite_scroll")
    val animatedOffset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 2f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 4000,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Restart
        ),
        label = "scroll_offset"
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
                    .widthForScreenPercentage(166.dp)
                    .clipToBounds(),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    modifier = Modifier
                        .offset(x = totalContentWidth * (1f - animatedOffset)),
                    horizontalArrangement = Arrangement.Center
                ) {
                    ColorBox(CertiTheme.colors.purpleBlue)
                    Spacer(Modifier.widthForScreenPercentage(16.dp))
                    ColorBox(CertiTheme.colors.skyBlue)
                    Spacer(Modifier.widthForScreenPercentage(74.dp))
                    ColorBox(CertiTheme.colors.subYellow)
                    Spacer(Modifier.widthForScreenPercentage(16.dp))
                    ColorBox(CertiTheme.colors.lightBlue)
                }

                Image(
                    painter = painterResource(R.drawable.img_loading),
                    contentDescription = null,
                    modifier = Modifier
                        .widthForScreenPercentage(118.dp)
                        .heightForScreenPercentage(110.dp)
                )
            }

            Spacer(Modifier.heightForScreenPercentage(20.dp))

            Text(
                text = stringResource(R.string.loading_title),
                style = CertiTheme.typography.body.semibold_16,
                color = CertiTheme.colors.gray600,
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.heightForScreenPercentage(24.dp))

            LottieAnimation(
                composition = composition,
                progress = { progress },
                modifier = Modifier.size(width = screenWidthDp(24.dp), height = screenHeightDp(24.dp))
            )
        }
    }
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

@Preview
@Composable
fun PreviewLoading() {
    LoadingLayout()
}
