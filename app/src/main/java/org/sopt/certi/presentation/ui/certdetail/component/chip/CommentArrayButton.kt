package org.sopt.certi.presentation.ui.certdetail.component.chip

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.certi.R
import org.sopt.certi.core.util.noRippleClickable
import org.sopt.certi.core.util.roundedBackgroundWithBorder
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.ui.theme.CertiTheme

enum class CommentArrayButtonType {
    Famous, Recent
}

@Composable
fun CommentArrayButton(
    commentArrayButtonType: CommentArrayButtonType,
    isSelected: Boolean,
    selectOnClick: (CommentArrayButtonType) -> Unit,
    modifier: Modifier = Modifier
) {
    val label = when (commentArrayButtonType) {
        CommentArrayButtonType.Famous -> stringResource(R.string.comment_label_famous)
        CommentArrayButtonType.Recent -> stringResource(R.string.comment_label_recent)
    }

    Box(
        modifier = modifier
            .roundedBackgroundWithBorder(
                cornerRadius = 24.dp,
                borderWidth = 1.dp,
                borderColor = if (isSelected) CertiTheme.colors.mainBlue else CertiTheme.colors.gray100,
                backgroundColor = CertiTheme.colors.white
            )
            .noRippleClickable {
                selectOnClick(commentArrayButtonType)
            }
    ) {
        Text(
            text = label,
            style = CertiTheme.typography.caption.semibold_12,
            color = if (isSelected) CertiTheme.colors.mainBlue else CertiTheme.colors.gray400,
            modifier = Modifier.padding(vertical = screenHeightDp(6.dp), horizontal = screenWidthDp(12.dp))
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewCommentArrayButton() {
    CommentArrayButton(
        commentArrayButtonType = CommentArrayButtonType.Recent,
        isSelected = true,
        selectOnClick = {}
    )
}
