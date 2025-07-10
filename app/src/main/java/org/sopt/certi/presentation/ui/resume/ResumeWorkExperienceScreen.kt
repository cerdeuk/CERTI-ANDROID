package org.sopt.certi.presentation.ui.resume

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.domain.model.ResumeListData
import org.sopt.certi.presentation.ui.resume.component.ResumeAddButton
import org.sopt.certi.presentation.ui.resume.component.ResumeEditListItem
import org.sopt.certi.ui.theme.CERTITheme

@Composable
fun ResumeWorkExperienceRoute(
    padding: PaddingValues,
    onNavigateToAdd: () -> Unit,
    viewModel: ResumeViewModel = hiltViewModel()
) {
    val resumeDataList = listOf(
        ResumeListData(
            startAt = "2021.11",
            endAt = "2022.01",
            organization = "서티그룹",
            role = "패션디자이너 인턴",
            description = "브랜드 리서치 및 소재 조사"
        ),
        ResumeListData(
            startAt = "2021.11",
            endAt = "2022.01",
            organization = "서티그룹",
            role = "패션디자이너 인턴",
            description = "브랜드 리서치 및 소재 조사"
        ),
        ResumeListData(
            startAt = "2021.11",
            endAt = "2022.01",
            organization = "서티그룹",
            role = "패션디자이너 인턴",
            description = "브랜드 리서치 및 소재 조사"
        )
    )

    ResumeWorkExperienceScreen(
        onNavigateToAdd = onNavigateToAdd,
        resumeDataList = resumeDataList,
        modifier = Modifier.padding(padding)
    )
}

@Composable
fun ResumeWorkExperienceScreen(
    onNavigateToAdd: () -> Unit,
    resumeDataList: List<ResumeListData>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Spacer(modifier = Modifier.padding(top = screenHeightDp(60.dp)))
        ResumeAddButton(
            onClick = onNavigateToAdd,
            modifier = Modifier.padding(start = screenWidthDp(20.dp))
        )

        LazyColumn(
            contentPadding = PaddingValues(
                horizontal = screenWidthDp(20.dp)
            ),
            verticalArrangement = Arrangement.spacedBy(screenHeightDp(36.dp))
        ) {
            items(resumeDataList) { resumeData ->
                ResumeEditListItem(
                    resumeListItem = resumeData,
                    onDeleteClick = {}
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewResumeWorkExperienceScreen() {
    val resumeDataList = listOf(
        ResumeListData(
            startAt = "2021.11",
            endAt = "2022.01",
            organization = "서티그룹",
            role = "패션디자이너 인턴",
            description = "브랜드 리서치 및 소재 조사"
        ),
        ResumeListData(
            startAt = "2021.11",
            endAt = "2022.01",
            organization = "서티그룹",
            role = "패션디자이너 인턴",
            description = "브랜드 리서치 및 소재 조사"
        ),
        ResumeListData(
            startAt = "2021.11",
            endAt = "2022.01",
            organization = "서티그룹",
            role = "패션디자이너 인턴",
            description = "브랜드 리서치 및 소재 조사"
        )
    )

    CERTITheme {
        ResumeWorkExperienceScreen(
            resumeDataList = resumeDataList,
            onNavigateToAdd = {}
        )
    }
}
