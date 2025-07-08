package org.sopt.certi.presentation.ui.login.component

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateValue
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.sopt.certi.ui.theme.CERTITheme

@Composable
fun BouncingImage(
    resId: Int,
    heightRatio: Float,
    widthRatio: Float,
    contentDescription: String? = null
) {
    val configuration = LocalConfiguration.current
    val infiniteTransition = rememberInfiniteTransition()

    val offsetY = infiniteTransition.animateValue(
        initialValue = 0.dp,
        targetValue = 10.dp,
        typeConverter = Dp.VectorConverter,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    ).value

    Image(
        painter = painterResource(id = resId),
        contentDescription = contentDescription,
        modifier = Modifier
            .offset(y = offsetY)
            .height((configuration.screenHeightDp * heightRatio).dp)
            .width((configuration.screenWidthDp * widthRatio).dp),
        contentScale = ContentScale.Fit
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewBouncingImage() {
    CERTITheme {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            BouncingImage(
                resId = org.sopt.certi.R.drawable.img_logo,
                heightRatio = 0.056f,
                widthRatio = 0.36f
            )
        }
    }
}
