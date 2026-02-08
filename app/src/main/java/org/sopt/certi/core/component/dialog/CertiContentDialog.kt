package org.sopt.certi.core.component.dialog


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import org.sopt.certi.R
import org.sopt.certi.core.util.heightForScreenPercentage
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun CertiContentDialog(
    titleText: String,
    contentText: String,
    onConfirmClick: () -> Unit,
    onDismissClick: () -> Unit
) {
    Dialog(onDismissRequest = onDismissClick) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .background(CertiTheme.colors.white)
                .padding(top = screenHeightDp(30.dp)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = titleText,
                style = CertiTheme.typography.body.semibold_16,
                color = CertiTheme.colors.gray600,
                modifier = Modifier.padding(horizontal = screenWidthDp(16.dp)),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.heightForScreenPercentage(16.dp))

            Text(
                text = contentText,
                style = CertiTheme.typography.caption.regular_14,
                color = CertiTheme.colors.gray600,
                modifier = Modifier.padding(horizontal = screenWidthDp(16.dp)),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.heightForScreenPercentage(26.dp))

            HorizontalDivider(
                thickness = 1.dp,
                color = CertiTheme.colors.gray100
            )

            Row(modifier = Modifier.height(IntrinsicSize.Max)) {
                DialogButton(
                    text = stringResource(R.string.delete_dialog_cancel),
                    textColor = CertiTheme.colors.black,
                    onClick = onDismissClick,
                    modifier = Modifier.weight(1f)
                )
                VerticalDivider(
                    thickness = 1.dp,
                    color = CertiTheme.colors.gray100
                )
                DialogButton(
                    text = stringResource(R.string.delete_dialog_confirm),
                    textColor = CertiTheme.colors.purpleBlue,
                    onClick = onConfirmClick,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewCertiContentDialog() {
    CertiContentDialog(
        titleText = "프리뷰 프리뷰",
        contentText = "콘텐트 콘텐트",
        onConfirmClick = {},
        onDismissClick = {}
    )
}