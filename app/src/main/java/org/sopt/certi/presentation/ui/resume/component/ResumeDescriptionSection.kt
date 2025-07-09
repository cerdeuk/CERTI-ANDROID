package org.sopt.certi.presentation.ui.resume.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import org.sopt.certi.R
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun ResumeDescriptionSection(
    startAt: String,
    endAt: String,
    organization: String,
    role: String,
    description: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = stringResource(R.string.resumelistitem_period, startAt, endAt),
                color = CertiTheme.colors.gray500,
                style = CertiTheme.typography.caption.regular_12
            )
            Spacer(modifier = Modifier.height(screenHeightDp(0.015f)))
            Text(
                text = organization,
                color = CertiTheme.colors.gray500,
                style = CertiTheme.typography.caption.regular_12
            )
        }
        Spacer(modifier = Modifier.width(screenWidthDp(0.09f)))

        Column {
            Text(
                text = role,
                color = CertiTheme.colors.gray600,
                style = CertiTheme.typography.body.semibold_16
            )
            Spacer(modifier = Modifier.height(screenHeightDp(0.01f)))
            Text(
                text = description,
                color = CertiTheme.colors.gray600,
                style = CertiTheme.typography.caption.regular_12
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ResumeDescriptionSectionPreview() {
    CERTITheme {
        ResumeDescriptionSection(
            startAt = "2021.11",
            endAt = "2022.01",
            organization = "서티그룹",
            role = "패션디자이너 인턴",
            description = "브랜드 리서치 및 소재 조사"
        )
    }
}
