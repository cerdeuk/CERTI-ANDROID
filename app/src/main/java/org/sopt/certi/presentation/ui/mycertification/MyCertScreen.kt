package org.sopt.certi.presentation.ui.mycertification

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import org.sopt.certi.domain.model.certification.CertificationData
import org.sopt.certi.ui.theme.CERTITheme
import java.time.LocalDate

@Composable
fun MyCertRoute(
    padding: PaddingValues,
    viewModel: MyCertViewModel = hiltViewModel()
) {
    val uiState by viewModel.myCertUiState.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CertificationScreen(
    certifications: ImmutableList<CertificationData>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {
        stickyHeader {
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewResumeMyCertScreen() {
    val dummyCertifications = listOf(
        CertificationData(
            certificationId = 1,
            certificationName = "GTQ 1급 (그래픽기술자격)",
            createdAt = LocalDate.now(),
            cardFrontImageUrl = "https://sopt-certi-bucket.s3.ap-northeast-2.amazonaws.com/certi/color%3Dblue.png",
            tags = listOf("태그", "태그", "태그")
        ),
        CertificationData(
            certificationId = 1,
            certificationName = "GTQ 1급 (그래픽기술자격)",
            createdAt = LocalDate.now(),
            cardFrontImageUrl = "https://sopt-certi-bucket.s3.ap-northeast-2.amazonaws.com/certi/color%3Dwhite.png",
            tags = listOf("태그", "태그", "태그")
        ),
        CertificationData(
            certificationId = 1,
            certificationName = "GTQ 1급 (그래픽기술자격)",
            createdAt = LocalDate.now(),
            cardFrontImageUrl = "https://sopt-certi-bucket.s3.ap-northeast-2.amazonaws.com/certi/color%3Dyellow.png",
            tags = listOf("태그", "태그", "태그")
        ),
        CertificationData(
            certificationId = 1,
            certificationName = "GTQ 1급 (그래픽기술자격)",
            createdAt = LocalDate.now(),
            cardFrontImageUrl = "https://sopt-certi-bucket.s3.ap-northeast-2.amazonaws.com/certi/color%3Dblue.png",
            tags = listOf("태그", "태그", "태그")
        )
    )

    CERTITheme {
        CertificationScreen(
            certifications = dummyCertifications.toImmutableList()
        )
    }
}
