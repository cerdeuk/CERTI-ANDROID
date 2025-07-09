package org.sopt.certi.presentation.ui.resume.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import org.sopt.certi.R
import org.sopt.certi.core.util.heightForScreenPercentage
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.widthForScreenPercentage
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun ResumeEmptyContent(
    text: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = screenHeightDp(0.076f)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.img_empty),
            contentDescription = null,
            modifier = Modifier
                .widthForScreenPercentage(0.36f)
                .heightForScreenPercentage(0.13f)
        )
        Spacer(modifier = Modifier.height(screenHeightDp(0.025f)))

        Text(
            text = text,
            style = CertiTheme.typography.caption.regular_14,
            color = CertiTheme.colors.gray400
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ResumeEmptyBoxPreview() {
    CERTITheme {
        ResumeEmptyContent(
            text = stringResource(R.string.resume_empty_experience_message)
        )
    }
}
