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
import androidx.compose.ui.unit.dp
import org.sopt.certi.R
import org.sopt.certi.core.util.noRippleClickable
import org.sopt.certi.domain.model.ResumeListData
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun ResumeEditListItem(
    resumeListItem: ResumeListData,
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ResumeDescriptionSection(
            resumeListItem = resumeListItem,
            modifier = Modifier
                .weight(1f)
                .padding(end = screenWidthDp(22.dp))
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
            resumeListItem = ResumeListData(
                startAt = "2021.11",
                endAt = "2022.01",
                organization = "서티그룹",
                role = "패션디자이너 인턴",
                description = "브랜드 리서치 및 소재 조사ㅇ"
            ),
            onDeleteClick = { }
        )
    }
}
