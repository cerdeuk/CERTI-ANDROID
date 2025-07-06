package org.sopt.certi.core.component

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import org.sopt.certi.R
import org.sopt.certi.core.util.noRippleClickable
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun DeleteDialog(
    showDialog: Boolean = false,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
    title: String = stringResource(R.string.delete_dialog_title),
    description: String = stringResource(R.string.delete_dialog_description)
) {
    val cancelInteractionSource = remember { MutableInteractionSource() }
    val confirmInteractionSource = remember { MutableInteractionSource() }

    if (showDialog) {
        Dialog(onDismissRequest = onDismiss) {
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = CertiTheme.colors.white
            ) {
                Column(
                    modifier = Modifier.padding(top = 32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = title,
                        style = CertiTheme.typography.body.semibold_18,
                        color = CertiTheme.colors.gray600,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                    Text(
                        text = description,
                        style = CertiTheme.typography.caption.regular_14,
                        color = CertiTheme.colors.gray600,
                        modifier = Modifier.padding(bottom = 26.dp)
                    )
                    Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(CertiTheme.colors.gray100))
                    Row(
                        modifier = Modifier.fillMaxWidth().height(IntrinsicSize.Max)
                    ) {
                        DialogButton(
                            text = stringResource(R.string.delete_dialog_cancel),
                            textColor = CertiTheme.colors.black,
                            onClick = onDismiss,
                            interactionSource = cancelInteractionSource,
                            modifier = Modifier.weight(1f)
                        )
                        Box(modifier = Modifier.width(1.dp).fillMaxHeight().background(CertiTheme.colors.gray100))
                        DialogButton(
                            text = stringResource(R.string.delete_dialog_confirm),
                            textColor = CertiTheme.colors.purpleBlue,
                            onClick = onConfirm,
                            interactionSource = confirmInteractionSource,
                            modifier = Modifier.weight(1f)
                        )
                    }
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
    interactionSource: MutableInteractionSource,
    modifier: Modifier = Modifier
) {
    val isPressed by interactionSource.collectIsPressedAsState()

    Box(
        modifier = modifier
            .background(if (isPressed) CertiTheme.colors.gray0 else CertiTheme.colors.white)
            .noRippleClickable(onClick, interactionSource),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = CertiTheme.typography.body.semibold_18,
            color = textColor,
            modifier = Modifier.padding(vertical = 20.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DeleteDialogPreview() {
    var showDialog by remember { mutableStateOf(true) }
    CERTITheme {
        DeleteDialog(
            showDialog = showDialog,
            onConfirm = { },
            onDismiss = { showDialog = false }
        )
    }
}
