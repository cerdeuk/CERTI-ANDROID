package org.sopt.certi.core.component.topbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.certi.R
import org.sopt.certi.core.util.noRippleClickable
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.core.util.widthForScreenPercentage
import org.sopt.certi.ui.theme.CERTITheme

@Composable
fun DDayoTopBar(
    modifier: Modifier = Modifier,
    logoutOnClick: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = screenWidthDp(20.dp), vertical = screenHeightDp(12.dp))
    ) {
        Image(
            painter = painterResource(R.drawable.img_logo_black),
            contentDescription = null,
            modifier = Modifier
                .widthForScreenPercentage(76.dp)
                .noRippleClickable {
                    logoutOnClick()
                }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun DDayoTopBarPreview() {
    CERTITheme {
        DDayoTopBar() {
        }
    }
}
