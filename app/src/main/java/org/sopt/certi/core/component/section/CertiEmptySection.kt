package org.sopt.certi.core.component.section

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.certi.R
import org.sopt.certi.core.util.heightForScreenPercentage
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.widthForScreenPercentage
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun CertiEmptySection(
    text: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.img_empty),
            contentDescription = null,
            modifier = Modifier
                .widthForScreenPercentage(130.dp)
                .heightForScreenPercentage(100.dp)
        )
        Spacer(modifier = Modifier.height(screenHeightDp(20.dp)))

        Text(
            text = text,
            style = CertiTheme.typography.caption.regular_14,
            color = CertiTheme.colors.gray400
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ResumeEmptyBoxPreview() {
    CERTITheme {
        CertiEmptySection(
            text = stringResource(R.string.resume_empty_experience_message)
        )
    }
}
