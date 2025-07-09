package org.sopt.certi.core.component.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import org.sopt.certi.R
import org.sopt.certi.core.util.pressedClickable
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun CertiDeleteDialog(
    onConfirmClick: () -> Unit,
    onDismissClick: () -> Unit,
    title: String = stringResource(R.string.delete_dialog_title),
    description: String = stringResource(R.string.delete_dialog_description)
) {
    Dialog(onDismissRequest = onDismissClick) {
        Surface(
            shape = RoundedCornerShape(12.dp),
            color = CertiTheme.colors.white
        ) {
            Column(
                modifier = Modifier.padding(top = screenHeightDp(32.dp)),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = title,
                    style = CertiTheme.typography.body.semibold_18,
                    color = CertiTheme.colors.gray600
                )
                Spacer(modifier = Modifier.height(screenHeightDp(16.dp)))
                Text(
                    text = description,
                    style = CertiTheme.typography.caption.regular_14,
                    color = CertiTheme.colors.gray600
                )
                Spacer(modifier = Modifier.height(screenHeightDp(24.dp)))
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
                        text = stringResource(R.string.delete_dialog_confirm),
                        textColor = CertiTheme.colors.purpleBlue,
                        onClick = onConfirmClick,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}

@Composable
fun DialogButton(
    text: String,
    textColor: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var isPressed by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .background(if (isPressed) CertiTheme.colors.gray0 else CertiTheme.colors.white)
            .pressedClickable(
                changePressed = { isPressed = it },
                onClick = onClick
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = CertiTheme.typography.body.semibold_18,
            color = textColor,
            modifier = Modifier.padding(vertical = screenHeightDp(20.dp))
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DeleteDialogPreview() {
    var showDialog by remember { mutableStateOf(true) }
    CERTITheme {
        if (showDialog) {
            CertiDeleteDialog(
                onConfirmClick = { },
                onDismissClick = { showDialog = false }
            )
        }
    }
}
