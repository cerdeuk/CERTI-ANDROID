package org.sopt.certi.presentation.ui.resume

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.certi.R
import org.sopt.certi.core.util.noRippleClickable
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun ResumeEditListItem(
    date: String,
    group: String,
    part: String,
    task: String,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .padding(vertical = 12.dp)
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = date,
                    color = CertiTheme.colors.gray500,
                    style = CertiTheme.typography.caption.regular_12
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = group,
                    color = CertiTheme.colors.gray500,
                    style = CertiTheme.typography.caption.regular_12
                )
            }
            Spacer(modifier = Modifier.width(22.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 58.dp)
            ) {
                Text(
                    text = part,
                    color = CertiTheme.colors.gray600,
                    style = CertiTheme.typography.body.semibold_16
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = task,
                    color = CertiTheme.colors.gray600,
                    style = CertiTheme.typography.caption.regular_12
                )
            }
        }
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_close_36),
            contentDescription = null,
            tint = CertiTheme.colors.gray500,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .noRippleClickable { onClick() }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ResumeEditListItemPreview() {
    CERTITheme {
        Column {
            ResumeEditListItem(
                date = "2021.11 ~ 2022.01",
                group = "서티그룹",
                part = "패션디자이너 인턴",
                task = "브랜드 리서치 및 소재 조사ㅇ",
                onClick = {}
            )
            ResumeEditListItem(
                date = "2021.11 ~ 2022.01",
                group = "sopt",
                part = "동아리 36기 기획",
                task = "서비스 기획 및 아이디어 도출",
                onClick = {}
            )
        }
    }
}
