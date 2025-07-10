package org.sopt.certi.presentation.ui.resume.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
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
import org.sopt.certi.core.util.noRippleClickable
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.core.util.widthForScreenPercentage
import org.sopt.certi.domain.model.ResumeListData
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun ResumeListSection(
    title: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    resumeListItems: List<ResumeListData>
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = screenHeightDp(36.dp))
            .padding(horizontal = screenWidthDp(20.dp))
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                style = CertiTheme.typography.subtitle.semibold_20,
                color = CertiTheme.colors.gray600,
                modifier = Modifier.weight(1f)
            )
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_arrowright_36),
                contentDescription = null,
                modifier = Modifier.noRippleClickable { onClick() }
            )
        }
        if (resumeListItems.isEmpty()) {
            ResumeEmptyContent(
                text = stringResource(R.string.resume_empty_experience_message)
            )
        } else {
            ResumeListContent(resumeListItems = resumeListItems)
        }
    }
}

@Composable
private fun ResumeListContent(
    resumeListItems: List<ResumeListData>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(
            top = screenHeightDp(16.dp),
            bottom = screenHeightDp(8.dp)
        ),
        verticalArrangement = Arrangement.spacedBy(screenHeightDp(24.dp))
    ) {
        resumeListItems.forEach { item ->
            ResumeListItem(
                resumeListItem = item
            )
        }
    }
}

@Composable
private fun ResumeListItem(
    resumeListItem: ResumeListData,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = screenHeightDp(12.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(R.drawable.img_resume_list_item),
            contentDescription = null,
            modifier = Modifier
                .widthForScreenPercentage(36.dp)
                .heightForScreenPercentage(36.dp)
        )
        Spacer(modifier = Modifier.width(screenWidthDp(24.dp)))

        ResumeDescriptionSection(resumeListItem = resumeListItem)
    }
}

@Preview(showBackground = true)
@Composable
private fun ResumeEmptyListSectionPreview() {
    CERTITheme {
        ResumeListSection(
            title = stringResource(R.string.resume_section_experience_title),
            onClick = { },
            resumeListItems = listOf()
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ResumeListSectionPreview() {
    CERTITheme {
        ResumeListSection(
            title = stringResource(R.string.resume_section_experience_title),
            onClick = { },
            resumeListItems = listOf(
                ResumeListData(
                    startAt = "2021.11",
                    endAt = "2022.01",
                    organization = "서티그룹",
                    role = "패션디자이너 인턴",
                    description = "트렌드 리서치 및 소재 조사"
                ),
                ResumeListData(
                    startAt = "2021.11",
                    endAt = "2022.01",
                    organization = "서티그룹",
                    role = "패션디자이너 인턴",
                    description = "트렌드 리서치 및 소재 조사"
                ),
                ResumeListData(
                    startAt = "2021.11",
                    endAt = "2022.01",
                    organization = "서티그룹",
                    role = "패션디자이너 인턴",
                    description = "트렌드 리서치 및 소재 조사"
                ),
                ResumeListData(
                    startAt = "2021.11",
                    endAt = "2022.01",
                    organization = "서티그룹",
                    role = "패션디자이너 인턴",
                    description = "트렌드 리서치 및 소재 조사"
                )
            )
        )
    }
}
