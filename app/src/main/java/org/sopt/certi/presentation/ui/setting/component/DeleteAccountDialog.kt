package org.sopt.certi.presentation.ui.setting.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import org.sopt.certi.R
import org.sopt.certi.core.component.dialog.DialogButton
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun DeleteAccountDialog(
    onConfirmClick: () -> Unit,
    onDismissClick: () -> Unit
) {
    Dialog(onDismissRequest = onDismissClick) {
        Surface(
            shape = RoundedCornerShape(12.dp),
            color = CertiTheme.colors.white
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    modifier = Modifier.padding(horizontal = screenWidthDp(34.dp), vertical = screenHeightDp(24.dp)),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(R.string.setting_delete_account_dialog_message),
                        style = CertiTheme.typography.body.semibold_18,
                        color = CertiTheme.colors.gray600
                    )

                    Spacer(modifier = Modifier.height(screenHeightDp(16.dp)))
                    Text(
                        text = stringResource(R.string.setting_delete_account_dialog_description),
                        style = CertiTheme.typography.caption.regular_14,
                        color = CertiTheme.colors.gray600
                    )
                }

                HorizontalDivider(
                    thickness = 1.dp,
                    color = CertiTheme.colors.gray100
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(IntrinsicSize.Max)
                ) {
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
                        text = stringResource(R.string.setting_delete_account_dialog_confirm),
                        textColor = CertiTheme.colors.error,
                        onClick = onConfirmClick,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DeleteAccountDialogPreview() {
    var showDialog by remember { mutableStateOf(true) }
    CERTITheme {
        if (showDialog) {
            DeleteAccountDialog(
                onConfirmClick = { },
                onDismissClick = { showDialog = false }
            )
        }
    }
}
