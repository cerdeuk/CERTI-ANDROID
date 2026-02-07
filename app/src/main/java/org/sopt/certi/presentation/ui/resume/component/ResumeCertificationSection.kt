package org.sopt.certi.presentation.ui.resume.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.certi.R
import org.sopt.certi.core.component.section.CertiEmptySection
import org.sopt.certi.core.util.noRippleClickable
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.domain.model.certification.CertificationData
import org.sopt.certi.presentation.ui.resume.component.card.ResumeCertificationSmallCard
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme
import java.time.LocalDate

@Composable
fun ResumeCertificationSection(
    title: String,
    onClick: () -> Unit,
    onCertificationClick: (Long) -> Unit,
    acquiredCertificationList: List<CertificationData>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = screenHeightDp(36.dp))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = screenWidthDp(20.dp)),
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
        if (acquiredCertificationList.isEmpty()) {
            CertiEmptySection(
                text = stringResource(R.string.resume_empty_certification_message),
                modifier = Modifier.padding(vertical = screenHeightDp(60.dp))
            )
        } else {
            ResumeCertificationContent(
                acquiredCertificationList = acquiredCertificationList,
                onCertificationClick = onCertificationClick
            )
        }
    }
}

@Composable
private fun ResumeCertificationContent(
    acquiredCertificationList: List<CertificationData>,
    onCertificationClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                top = screenHeightDp(16.dp),
                bottom = screenHeightDp(36.dp)
            ),
        horizontalArrangement = Arrangement.spacedBy(screenWidthDp(12.dp)),
        contentPadding = PaddingValues(horizontal = screenWidthDp(20.dp))
    ) {
        itemsIndexed(acquiredCertificationList) { index, certification ->
            ResumeCertificationSmallCard(
                certification = certification,
                onClick = { certification.acquisitionId?.let { onCertificationClick(it) } }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ResumeEmptyCertificationSectionPreview() {
    CERTITheme {
        ResumeListSection(
            title = stringResource(R.string.resume_section_experience_title),
            onClick = { },
            emptyText = stringResource(R.string.resume_empty_certification_message),
            resumeListItems = listOf(),
            bottomPadding = 13.dp
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ResumeCertificationSectionPreview() {
    CERTITheme {
        ResumeCertificationSection(
            title = stringResource(R.string.resume_section_experience_title),
            onClick = { },
            onCertificationClick = {},
            acquiredCertificationList = listOf(
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
        )
    }
}
