package org.sopt.certi.presentation.ui.mycertification.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.certi.R
import org.sopt.certi.core.component.section.CertInfoSection
import org.sopt.certi.core.component.section.CertItemTitleSection
import org.sopt.certi.core.util.noRippleClickable
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.domain.model.certification.CertificationData
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme
import java.time.LocalDate

@Composable
fun FavoriteCertItem(
    certificationData: CertificationData,
    onCertificationClick: (Long) -> Unit,
    onFavoriteToggle: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .noRippleClickable { onCertificationClick(certificationData.certificationId) }
            .border(
                width = 1.dp,
                color = CertiTheme.colors.lightPurple,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(screenWidthDp(16.dp)),
        verticalArrangement = Arrangement.spacedBy(screenWidthDp(12.dp))
    ) {
        CertItemTitleSection(
            certName = certificationData.certificationName,
            certType = certificationData.certificationType,
            isFavorite = certificationData.isFavorite,
            onFavoriteClick = { onFavoriteToggle(certificationData.certificationId) }
        )

        Row(
            modifier = Modifier.padding(vertical = screenWidthDp(2.dp)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(screenWidthDp(8.dp))
        ) {
            CertInfoSection(
                iconRes = R.drawable.ic_paper_16,
                testInfo = certificationData.testType
            )
            CertInfoSection(
                iconRes = R.drawable.ic_certification_16,
                testInfo = certificationData.agencyName
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun FavoriteCertItemPreview() {
    CERTITheme {
        FavoriteCertItem(
            certificationData = CertificationData(
                certificationId = 1,
                certificationName = "정보처리기사",
                certificationType = "국가기술자격",
                description = "소프트웨어 개발 관련 자격증으로, 계획수립, 분석, 설계, 구...",
                isAcquired = true,
                city = "고양시",
                testTime = "09:00",
                createdAt = LocalDate.of(2025, 11, 23),
                level = "IM3",
                isFavorite = true,
                testType = "실기형",
                agencyName = "한국산업인력공단"
            ),
            onCertificationClick = {},
            onFavoriteToggle = {}
        )
    }
}
