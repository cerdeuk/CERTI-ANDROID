package org.sopt.certi.presentation.ui.resume.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import org.sopt.certi.R
import org.sopt.certi.core.util.noRippleClickable
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun ResumeEditListItem(
    startAt: String,
    endAt: String,
    organization: String,
    role: String,
    description: String,
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ResumeDescriptionSection(
            startAt = startAt,
            endAt = endAt,
            organization = organization,
            role = role,
            description = description,
            modifier = Modifier
                .weight(1f)
                .padding(end = screenWidthDp(0.06f))
        )

        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_close_36),
            contentDescription = null,
            tint = CertiTheme.colors.gray500,
            modifier = Modifier
                .noRippleClickable { onDeleteClick() }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ResumeEditListItemPreview() {
    CERTITheme {
        ResumeEditListItem(
            startAt = "2021.11",
            endAt = "2022.01",
            organization = "서티그룹",
            role = "패션디자이너 인턴",
            description = "브랜드 리서치 및 소재 조사ㅇ",
            onDeleteClick = { }
        )
    }
}
