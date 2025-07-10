package org.sopt.certi.presentation.ui.resume.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.certi.R
import org.sopt.certi.core.util.noRippleClickable
import org.sopt.certi.core.util.roundedBackgroundWithBorder
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun ResumeAddButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .roundedBackgroundWithBorder(
                cornerRadius = 8.dp,
                backgroundColor = CertiTheme.colors.purpleWhite
            )
            .padding(vertical = screenHeightDp(12.dp), horizontal = screenWidthDp(20.dp))
            .noRippleClickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_resume_add_24),
            contentDescription = null,
            tint = CertiTheme.colors.mainBlue
        )
        Spacer(modifier = Modifier.width(screenWidthDp(2.dp)))
        Text(
            text = stringResource(R.string.resume_add_button),
            style = CertiTheme.typography.body.bold_16,
            color = CertiTheme.colors.mainBlue
        )
    }
}

@Preview
@Composable
private fun PreviewResumeButton() {
    CERTITheme {
        ResumeAddButton(onClick = {})
    }
}
