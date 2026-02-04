package org.sopt.certi.presentation.ui.certdetail.component.comment

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.certi.R
import org.sopt.certi.core.util.heightForScreenPercentage
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun CommentEmptyView(
    modifier : Modifier = Modifier
) {
    Column (
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(R.drawable.img_empty),
            contentDescription = null,
        )

        Spacer(Modifier.heightForScreenPercentage(20.dp))

        Text(
            text = stringResource(R.string.comment_empty),
            style = CertiTheme.typography.caption.regular_14,
            color = CertiTheme.colors.gray400,
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
private fun PreviewCommentEmptyView() {
    CommentEmptyView()
}