package org.sopt.certi.presentation.ui.certdetail.component.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import org.sopt.certi.R
import org.sopt.certi.core.util.heightForScreenPercentage
import org.sopt.certi.core.util.noRippleClickable
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.core.util.widthForScreenPercentage
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun ReportCommentDialog(
    onReportClick: (content: String, block: Boolean) -> Unit,
    onDismissClick: () -> Unit
) {
    var contentText by remember { mutableStateOf("") }
    var blockState by remember { mutableStateOf(false) }

    Dialog(onDismissRequest = onDismissClick) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .background(CertiTheme.colors.white)
                .padding(
                    top = screenHeightDp(18.dp),
                    bottom = screenHeightDp(20.dp),
                    start = screenHeightDp(20.dp),
                    end = screenHeightDp(20.dp)
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = stringResource(R.string.dialog_comment_report_title),
                    style = CertiTheme.typography.caption.semibold_14,
                    color = CertiTheme.colors.black
                )

                Spacer(Modifier.weight(1f))

                Icon(
                    painter = painterResource(R.drawable.ic_close_20),
                    contentDescription = null,
                    tint = Color.Unspecified,
                    modifier = Modifier
                        .widthForScreenPercentage(24.dp)
                        .heightForScreenPercentage(24.dp)
                )
            }

            Spacer(Modifier.heightForScreenPercentage(20.dp))

            Text(
                text = stringResource(R.string.dialog_comment_report_content_title),
                style = CertiTheme.typography.caption.semibold_12,
                color = CertiTheme.colors.gray400,
                modifier = Modifier.align(Alignment.Start)
            )

            Spacer(Modifier.heightForScreenPercentage(4.dp))

            BasicTextField(
                value = contentText,
                onValueChange = {
                    if (contentText.length < 100) {
                        contentText = it
                    }
                },
                decorationBox = { innerTextField ->
                    if (contentText.isEmpty()) {
                        Text(
                            text = stringResource(R.string.dialog_comment_report_content_hint),
                            style = CertiTheme.typography.caption.regular_12,
                            color = CertiTheme.colors.gray300
                        )
                    }
                    innerTextField()
                },
                cursorBrush = SolidColor(CertiTheme.colors.gray600),
                textStyle = CertiTheme.typography.caption.regular_12.copy(
                    color = CertiTheme.colors.gray600
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .defaultMinSize(minHeight = screenHeightDp(114.dp))
                    .clip(RoundedCornerShape(12.dp))
                    .background(CertiTheme.colors.gray0)
                    .padding(vertical = screenHeightDp(12.dp), horizontal = screenHeightDp(12.dp))
            )

            Spacer(Modifier.heightForScreenPercentage(4.dp))

            Text(
                text = "${contentText.length}/100",
                style = CertiTheme.typography.caption.regular_10,
                color = CertiTheme.colors.gray300,
                modifier = Modifier.align(Alignment.End)
            )

            Spacer(Modifier.heightForScreenPercentage(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    painter = painterResource(if (blockState) R.drawable.ic_checkbox_fill else R.drawable.ic_checkbox_empty),
                    contentDescription = null,
                    tint = Color.Unspecified,
                    modifier = Modifier
                        .noRippleClickable {
                            blockState = !blockState
                        }
                )

                Spacer(Modifier.widthForScreenPercentage(8.dp))

                Text(
                    text = stringResource(R.string.dialog_comment_report_block_title),
                    style = CertiTheme.typography.caption.semibold_12,
                    color = CertiTheme.colors.gray600
                )
            }

            Spacer(Modifier.heightForScreenPercentage(8.dp))

            Row {
                Text(
                    text = " ∙ ",
                    style = CertiTheme.typography.caption.regular_12,
                    color = CertiTheme.colors.gray400
                )

                Text(
                    text = stringResource(R.string.dialog_comment_report_note_p1),
                    style = CertiTheme.typography.caption.regular_12,
                    color = CertiTheme.colors.gray400
                )
            }

            Spacer(Modifier.heightForScreenPercentage(8.dp))

            Row {
                Text(
                    text = " ∙ ",
                    style = CertiTheme.typography.caption.regular_12,
                    color = CertiTheme.colors.gray400
                )

                Text(
                    text = stringResource(R.string.dialog_comment_report_note_p2),
                    style = CertiTheme.typography.caption.regular_12,
                    color = CertiTheme.colors.gray400
                )
            }

            Spacer(Modifier.heightForScreenPercentage(14.dp))

            Box(
                modifier = Modifier
                    .background(
                        color = CertiTheme.colors.white,
                        shape = RoundedCornerShape(100.dp)
                    )
                    .border(width = 1.dp, color = CertiTheme.colors.mainBlue, shape = RoundedCornerShape(100.dp))
                    .padding(vertical = screenHeightDp(8.dp), horizontal = screenWidthDp(22.dp))
                    .align(Alignment.End)
                    .noRippleClickable {
                        onReportClick(contentText, blockState)
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(R.string.dialog_comment_report_confirm),
                    style = CertiTheme.typography.caption.semibold_12,
                    color = CertiTheme.colors.mainBlue
                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewReportCommentDialog() {
    ReportCommentDialog(
        onReportClick = { _, _ ->
        },
        onDismissClick = {
        }
    )
}
