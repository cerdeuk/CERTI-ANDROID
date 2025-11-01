package org.sopt.certi.presentation.ui.certlist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import org.sopt.certi.R
import org.sopt.certi.core.state.UiState
import org.sopt.certi.core.util.noRippleClickable
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.domain.model.certification.CertificationData
import org.sopt.certi.presentation.ui.certlist.component.CategoryTopBar
import org.sopt.certi.presentation.ui.certlist.state.CertListUiState
import org.sopt.certi.presentation.ui.home.component.RecommendedCertificationListSection
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun CertListRoute(
    padding: PaddingValues,
    navigateToSearch: () -> Unit,
    navigateToCertDetail: (certId: Long) -> Unit,
    navigateToMore: (mode: String) -> Unit,
    viewModel: CertListViewModel = hiltViewModel()
) {
    val uiState by viewModel.certificationListUiState.collectAsStateWithLifecycle()

    CertListScreen(
        certListState = uiState,
        navigateToSearch = navigateToSearch,
        navigateToCertDetail = navigateToCertDetail,
        navigateToMore = navigateToMore,
        recommendedList = persistentListOf(),
        modifier = Modifier.padding(padding)
    )
}

@Composable
private fun CertListScreen(
    certListState: CertListUiState,
    navigateToSearch: () -> Unit,
    navigateToCertDetail: (Long) -> Unit,
    navigateToMore: (mode: String) -> Unit,
    recommendedList: ImmutableList<CertificationData>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        CategoryTopBar(
            title = R.string.cert_list_top_bar,
            onClick = navigateToSearch,
            modifier = Modifier.padding(horizontal = screenWidthDp(20.dp))
        )

        Text(
            text = "계열별",
            modifier = Modifier.noRippleClickable { navigateToMore("track") }
        )

        Text(
            text = "직무별",
            modifier = Modifier.noRippleClickable { navigateToMore("category") }
        )

        CertListRecommendSection(
            recommendedList = recommendedList,
            onDetailClick = { certId ->
                navigateToCertDetail(certId)
            },
            modifier = Modifier.padding(start = screenWidthDp(20.dp), end = screenWidthDp(20.dp), top = screenHeightDp(24.dp), bottom = screenHeightDp(30.dp))
        )
    }
}

@Composable
private fun CertListRecommendSection(
    recommendedList: ImmutableList<CertificationData>,
    onDetailClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        item {
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(R.string.cert_list_recommend_title),
                        style = CertiTheme.typography.subtitle.bold_20,
                        color = CertiTheme.colors.gray600,
                        modifier = Modifier.padding(end = screenWidthDp(4.dp))
                    )

                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.ic_information_24),
                        contentDescription = null,
                        tint = Color.Unspecified
                    )
                }

                RecommendedCertificationListSection(
                    recommendedList = recommendedList,
                    onDetailClick = { certId ->
                        onDetailClick(certId)
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewCertListScreen() {
    CERTITheme {
        var selectedCategory by remember { mutableIntStateOf(0) }
        var certificationList by remember {
            mutableStateOf(
                listOf(
                    CertificationData(
                        certificationId = 1,
                        certificationName = "정보처리기사",
                        tags = listOf("시각디자인", "컴퓨터공학", "경영"),
                        isFavorite = true,
                        testType = "실기형",
                        agencyName = "국가기술자격",
                        description = "자격증 설명입니다. 자격증 설명입니다. 자격증 설명입니다. 자격증 설명입니다.자격증 설명입니다. 자격증 설명입니다. 자격증 설명입니다. 자격증 설명입니다.자격증 설명입니다. 자격증 설명입니다. 자격증 설명입니다. 자격증 설명입니다."
                    ),
                    CertificationData(
                        certificationId = 2,
                        certificationName = "GTQ 1급 (그래픽 기술 자격)",
                        tags = listOf("시각디자인", "컴퓨터공학", "경영"),
                        isFavorite = false,
                        testType = "실기형",
                        agencyName = "국가기술자격",
                        description = "자격증 설명입니다. 자격증 설명입니다. 자격증 설명입니다. 자격증 설명입니다.자격증 설명입니다. 자격증 설명입니다. 자격증 설명입니다. 자격증 설명입니다.자격증 설명입니다. 자격증 설명입니다. 자격증 설명입니다. 자격증 설명입니다."
                    ),
                    CertificationData(
                        certificationId = 3,
                        certificationName = "TOEIC 900+",
                        tags = listOf("경영", "시각디자인", "컴퓨터공학"),
                        isFavorite = true,
                        testType = "실기형",
                        agencyName = "국가기술자격",
                        description = "자격증 설명입니다. 자격증 설명입니다. 자격증 설명입니다. 자격증 설명입니다.자격증 설명입니다. 자격증 설명입니다. 자격증 설명입니다. 자격증 설명입니다.자격증 설명입니다. 자격증 설명입니다. 자격증 설명입니다. 자격증 설명입니다."
                    )
                )
            )
        }

        val uiState = CertListUiState(
            certificationListLoadState = if (certificationList.isEmpty()) UiState.Empty else UiState.Success(certificationList),
            selectedCategory = selectedCategory,
            isFavorite = false
        )

        CertListScreen(
            certListState = uiState,
            navigateToSearch = { },
            navigateToCertDetail = { },
            navigateToMore = { },
            recommendedList = persistentListOf()
        )
    }
}
