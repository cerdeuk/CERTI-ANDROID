package org.sopt.certi.core.component.topbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import org.sopt.certi.R
import org.sopt.certi.core.util.heightForScreenPercentage
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.core.util.widthForScreenPercentage
import org.sopt.certi.ui.theme.CERTITheme

@Composable
fun CertiTopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = screenWidthDp(0.06f), vertical = screenHeightDp(0.03f))
    ) {
        Image(
            painter = painterResource(R.drawable.img_logo_black),
            contentDescription = null,
            modifier = Modifier
                .heightForScreenPercentage(0.03f)
                .widthForScreenPercentage(0.21f)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CertiTopBarPreview() {
    CERTITheme {
        CertiTopBar()
    }
}
