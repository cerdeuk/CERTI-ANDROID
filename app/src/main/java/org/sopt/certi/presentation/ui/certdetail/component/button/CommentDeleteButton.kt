package org.sopt.certi.presentation.ui.certdetail.component.button

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.certi.R
import org.sopt.certi.core.util.pressedClickable
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun CommentDeleteButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    var isPressed by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .background(
                color = if (isPressed) CertiTheme.colors.gray0 else CertiTheme.colors.gray0,
                shape = RoundedCornerShape(12.dp)
            )
            .clip(RoundedCornerShape(16.dp))
            .padding(horizontal = screenWidthDp(8.dp), vertical = screenHeightDp(2.dp))
            .pressedClickable(
                changePressed = {
                    isPressed = it
                },
                onClick = onClick
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(R.string.comment_delete_button),
            style = CertiTheme.typography.caption.regular_12,
            color = CertiTheme.colors.black
        )
    }
}

@Preview
@Composable
private fun PreviewCommentDeleteButton() {
    CommentDeleteButton() { }
}
