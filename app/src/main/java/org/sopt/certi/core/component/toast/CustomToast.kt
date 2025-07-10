package org.sopt.certi.core.component.toast

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import org.sopt.certi.core.util.heightForScreenPercentage
import org.sopt.certi.core.util.noRippleClickable
import org.sopt.certi.core.util.roundedBackgroundWithBorder
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.core.util.widthForScreenPercentage
import org.sopt.certi.presentation.model.ToastConfig
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun ShowToastRoute(
    toastConfig: ToastConfig,
    showToastVisibility: (Boolean) -> Unit
) {
    LaunchedEffect(Unit) {
        delay(2000)
        showToastVisibility(false)
        toastConfig.endToastAction()
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        CustomToastLayout(toastConfig)
    }
}

@Composable
private fun CustomToastLayout(
    toastConfig: ToastConfig
) {
    Box(
        modifier = Modifier
            .padding(
                bottom = screenHeightDp(toastConfig.yOffset),
                start = screenWidthDp(30.dp),
                end = screenWidthDp(30.dp)
            )
            .roundedBackgroundWithBorder(
                backgroundColor = CertiTheme.colors.black85,
                cornerRadius = 12.dp
            )
            .widthIn(min = screenWidthDp(300.dp))
            .padding(
                horizontal = screenWidthDp(26.dp),
                vertical = screenHeightDp(22.dp)
            )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(toastConfig.iconId),
                contentDescription = null,
                tint = Color.Unspecified
            )
            Spacer(Modifier.widthForScreenPercentage(16.dp))
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = toastConfig.titleMessage,
                    style = CertiTheme.typography.caption.semibold_14,
                    color = CertiTheme.colors.gray0
                )
                Spacer(Modifier.heightForScreenPercentage(8.dp))
                Text(
                    text = toastConfig.contentMessage,
                    style = CertiTheme.typography.caption.regular_14,
                    color = CertiTheme.colors.gray400
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCustomToast() {
    var showToast by remember { mutableStateOf(false) }
    Box(Modifier.fillMaxSize().noRippleClickable { showToast = true })

    if (showToast) {
        ShowToastRoute(
            toastConfig = ToastConfig(
                titleMessage = "aaa",
                contentMessage = "bbbb",
                endToastAction = { showToast = false }
            ),
            showToastVisibility = { showToast = it }
        )
    }
}
