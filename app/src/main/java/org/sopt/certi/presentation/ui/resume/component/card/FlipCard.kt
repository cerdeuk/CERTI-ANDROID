package org.sopt.certi.presentation.ui.resume.component.card

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.certi.core.util.noRippleClickable
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.domain.model.CertificationData
import org.sopt.certi.domain.model.UserInfoData

import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme
import java.time.LocalDate

@Composable
fun FlipCardOverlay(
    certificationData: CertificationData,
    userInfo: UserInfoData,
    onDismiss: () -> Unit
) {
    var isFlipped by remember { mutableStateOf(false) }

    val rotationY by animateFloatAsState(
        targetValue = if (isFlipped) 180f else 0f,
        animationSpec = tween(500, easing = FastOutSlowInEasing),
        label = "rotationY"
    )
    val cameraDistance = with(LocalDensity.current) { 12.dp.toPx() }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(CertiTheme.colors.black.copy(alpha = 0.4f))
            .noRippleClickable { onDismiss() }
    )

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(width = screenWidthDp(250.dp), height = screenHeightDp(376.dp))
                .graphicsLayer {
                    this.rotationY = rotationY
                    this.cameraDistance = cameraDistance
                    if (rotationY > 90f) {
                        scaleX = -1f
                    }
                }
                .clip(RoundedCornerShape(12.dp))
                .background(CertiTheme.colors.subYellow)
                .noRippleClickable { isFlipped = !isFlipped }
        ) {
            if (rotationY <= 90f) {
                CertificationCardFront(
                    certificationData = certificationData
                )
            } else {
                CertificationCardBack(
                    certificationData = certificationData,
                    userInfo = userInfo
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FlipCardInteractivePreview() {
    var showCard by remember { mutableStateOf(true) }

    val certificationData = CertificationData(
        certificationId = 1,
        certificationName = "GTQ 1급 (그래픽기술자격)",
        createdAt = LocalDate.now(),
        description = "• 1급과 2급, 급수의 차이는 이 업무를 수행하는 툴 활용 능력의 범위와 숙련도 등의 고도화 차이다.",
        cardFrontImageUrl = "https://sopt-certi-bucket.s3.ap-northeast-2.amazonaws.com/certi/color%3Dblue.png",
        cardBackImageUrl = "",
        tags = listOf("디자인", "컴퓨터", "김민지")
    )
    val userInfo = UserInfoData(
        name = "김민지",
        university = "",
        major = ""
    )

    CERTITheme {
        if (showCard) {
            FlipCardOverlay(
                certificationData = certificationData,
                userInfo = userInfo,
                onDismiss = { showCard = false }
            )
        }
    }
}
