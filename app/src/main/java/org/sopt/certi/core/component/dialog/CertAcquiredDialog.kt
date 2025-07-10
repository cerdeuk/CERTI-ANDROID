package org.sopt.certi.core.component.dialog

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import org.sopt.certi.R
import org.sopt.certi.core.util.heightForScreenPercentage
import org.sopt.certi.core.util.noRippleClickable
import org.sopt.certi.core.util.roundedBackgroundWithBorder
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.widthForScreenPercentage
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun CertAcquiredDialog(
    certName: String,
    onConfirmClick: () -> Unit,
    setShowDialog: (Boolean) -> Unit = { }
) {
    Dialog(onDismissRequest = { setShowDialog(false) }) {
        Surface(
            color = CertiTheme.colors.white,
            shape = RoundedCornerShape(12.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(
                        top = screenHeightDp(40.dp),
                        bottom = screenHeightDp(26.dp),
                        start = screenHeightDp(28.dp),
                        end = screenHeightDp(28.dp)
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.acquired_dialog_title),
                    style = CertiTheme.typography.body.bold_18,
                    color = CertiTheme.colors.gray600
                )
                Spacer(Modifier.heightForScreenPercentage(12.dp))
                Text(
                    text = certName,
                    style = CertiTheme.typography.caption.semibold_14,
                    color = CertiTheme.colors.gray400
                )
                Spacer(Modifier.heightForScreenPercentage(4.dp))
                Image(
                    painter = painterResource(id = R.drawable.img_certification_card_get),
                    contentDescription = null,
                    modifier = Modifier
                        .widthForScreenPercentage(120.dp)
                        .heightForScreenPercentage(184.dp)
                )
                Spacer(Modifier.heightForScreenPercentage(8.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .roundedBackgroundWithBorder(backgroundColor = CertiTheme.colors.gray500, cornerRadius = 12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(R.string.acquired_dialog_confirm),
                        style = CertiTheme.typography.caption.semibold_14,
                        color = CertiTheme.colors.white,
                        modifier = Modifier
                            .padding(vertical = screenHeightDp(12.dp))
                            .noRippleClickable {
                                setShowDialog(false)
                                onConfirmClick()
                            }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCertAcquiredDialog() {
    var showDialog by remember { mutableStateOf(true) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .noRippleClickable { showDialog = true }
    )
    if (showDialog) {
        CertAcquiredDialog(
            certName = "자격증 이름",
            onConfirmClick = { },
            setShowDialog = { showDialog = it }
        )
    }
}
