package org.sopt.certi.presentation.ui.resume.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.certi.R
import org.sopt.certi.core.util.heightForScreenPercentage
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.core.util.widthForScreenPercentage
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun ResumeProfile(
    name: String,
    university: String,
    major: String,
    birthday: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.padding(horizontal = screenWidthDp(20.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(R.drawable.img_profile),
            contentDescription = null,
            modifier = Modifier
                .widthForScreenPercentage(80.dp)
                .heightForScreenPercentage(80.dp)
        )
        Spacer(modifier = Modifier.width(screenWidthDp(16.dp)))

        Column(
            verticalArrangement = Arrangement.spacedBy(screenHeightDp(8.dp))
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = name,
                    style = CertiTheme.typography.body.semibold_16,
                    color = CertiTheme.colors.gray600
                )

                Spacer(modifier = Modifier.weight(1f))

                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_edit_16),
                    contentDescription = null,
                    tint = CertiTheme.colors.gray400
                )

                Spacer(modifier = Modifier.widthForScreenPercentage(3.dp))

                Text(
                    text = stringResource(R.string.resume_profile_edit),
                    style = CertiTheme.typography.caption.semibold_12,
                    color = CertiTheme.colors.gray400
                )
            }

            ResumeProfileItem(
                label = stringResource(R.string.resume_profile_university),
                value = university
            )

            ResumeProfileItem(
                label = stringResource(R.string.resume_profile_major),
                value = major
            )

            ResumeProfileItem(
                label = stringResource(R.string.resume_profile_birthday),
                value = birthday
            )
        }
    }
}

@Composable
private fun ResumeProfileItem(
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.height(IntrinsicSize.Min),
        horizontalArrangement = Arrangement.spacedBy(screenWidthDp(8.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            style = CertiTheme.typography.caption.regular_14,
            color = CertiTheme.colors.gray400
        )

        VerticalDivider(modifier = Modifier.padding(vertical = screenHeightDp(3.dp)))

        Text(
            text = value,
            style = CertiTheme.typography.caption.semibold_14,
            color = CertiTheme.colors.gray600
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ResumeProfilePreview() {
    ResumeProfile(
        name = "김서티",
        university = "서티대학교",
        major = "시각디자인학과",
        birthday = "2001. 03. 26 (만 24세)"
    )
}
