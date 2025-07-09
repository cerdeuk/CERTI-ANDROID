package org.sopt.certi.presentation.ui.resume.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.domain.model.ResumeListData
import org.sopt.certi.ui.theme.CERTITheme

@Composable
fun ResumeListContent(
    resumeListItems: List<ResumeListData>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(
            top = screenHeightDp(0.02f),
            bottom = screenHeightDp(0.01f)
        ),
        verticalArrangement = Arrangement.spacedBy(screenHeightDp(0.03f))
    ) {
        resumeListItems.forEach { item ->
            ResumeListItem(
                resumeListItem = item
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ResumeExperienceListPreview() {
    CERTITheme {
        ResumeListContent(
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
