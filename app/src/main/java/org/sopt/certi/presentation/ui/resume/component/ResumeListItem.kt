package org.sopt.certi.presentation.ui.resume.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.certi.R
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun ResumeListItem(
    period: String,
    organization: String,
    role: String,
    description: String
) {
    Row(
        modifier = Modifier.padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(R.drawable.img_resume_list_item),
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(24.dp))

        Column {
            Text(
                text = period,
                color = CertiTheme.colors.gray500,
                style = CertiTheme.typography.caption.regular_12
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = organization,
                color = CertiTheme.colors.gray500,
                style = CertiTheme.typography.caption.regular_12
            )
        }
        Spacer(modifier = Modifier.width(32.dp))

        Column {
            Text(
                text = role,
                color = CertiTheme.colors.gray600,
                style = CertiTheme.typography.body.semibold_16
            )
            Spacer(modifier = Modifier.height(10.dp))
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
fun ResumeListItemPreview() {
    CERTITheme {
        ResumeListItem(
            period = "2021.11 ~ 2022.01",
            organization = "서티그룹",
            role = "패션디자이너 인턴",
            description = "브랜드 리서치 및 소재 조사"
        )
    }
}
