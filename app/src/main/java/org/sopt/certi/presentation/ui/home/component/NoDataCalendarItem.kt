package org.sopt.certi.presentation.ui.home.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.certi.R
import org.sopt.certi.core.util.heightForScreenPercentage
import org.sopt.certi.core.util.noRippleClickable
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.widthForScreenPercentage
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun NoDataCalendarItem(
    onAddClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = screenHeightDp(36.dp)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.home_calendar_no_data),
            style = CertiTheme.typography.caption.regular_14,
            color = CertiTheme.colors.gray300,
            textAlign = TextAlign.Center
        )

        Spacer(Modifier.heightForScreenPercentage(12.dp))

        Icon(
            painter = painterResource(R.drawable.ic_add),
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier
                .widthForScreenPercentage(36.dp)
                .heightForScreenPercentage(36.dp)
                .noRippleClickable {
                    onAddClick()
                }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewNoDataCalendarItem() {
    NoDataCalendarItem() {}
}
