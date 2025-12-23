package org.sopt.certi.presentation.ui.mycertification.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.certi.R
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.domain.model.certification.CertificationData
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme
import java.time.LocalDate

@Composable
fun MyCertItem(
    certificationData: CertificationData,
    isEditMode: Boolean,
    modifier: Modifier = Modifier,
    onEditClick: (Long) -> Unit,
    onDeleteClick: (Long) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .border(
                width = 1.dp,
                color = CertiTheme.colors.lightPurple,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(screenWidthDp(16.dp))
    ) {
        CertStatus(
            isAcquired = certificationData.isAcquired,
            modifier = Modifier.padding(bottom = screenWidthDp(8.dp)),
            isEditMode = isEditMode,
            onEditClick = { onEditClick(certificationData.certificationId) },
            onDeleteClick = { onDeleteClick(certificationData.certificationId) }
        )

        CertItemTitle(
            certName = certificationData.certificationName,
            certType = certificationData.certificationType,
            modifier = Modifier.padding(bottom = screenWidthDp(4.dp))
        )

        Text(
            text = certificationData.description,
            style = CertiTheme.typography.caption.regular_12,
            color = CertiTheme.colors.gray500,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            modifier = Modifier.padding(bottom = screenWidthDp(12.dp))
        )

        Row(
            modifier = Modifier.padding(vertical = screenWidthDp(2.dp)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(screenWidthDp(8.dp))
        ) {
            if (certificationData.isAcquired) {
                CertInfo(
                    iconRes = R.drawable.ic_date_16,
                    testInfo = certificationData.createdAt.toString()
                )
                CertInfo(
                    iconRes = R.drawable.ic_level,
                    testInfo = certificationData.level
                )
            } else {
                CertInfo(
                    iconRes = R.drawable.ic_placemark,
                    testInfo = certificationData.placement
                )
                CertInfo(
                    iconRes = R.drawable.ic_time,
                    testInfo = certificationData.testTime
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MyCertItemPreview() {
    CERTITheme {
        Column {
            MyCertItem(
                certificationData = CertificationData(
                    certificationId = 1,
                    certificationName = "정보처리기사",
                    certificationType = "국가기술자격",
                    description = "소프트웨어 개발 관련 자격증으로, 계획수립, 분석, 설계, 구...",
                    isAcquired = true,
                    placement = "고양시",
                    testTime = "09:00",
                    createdAt = LocalDate.of(2025, 11, 23),
                    level = "IM3"
                ),
                isEditMode = false,
                onEditClick = {},
                onDeleteClick = {}
            )
            MyCertItem(
                certificationData = CertificationData(
                    certificationId = 1,
                    certificationName = "정보처리기사",
                    certificationType = "국가기술자격",
                    description = "소프트웨어 개발 관련 자격증으로, 계획수립, 분석, 설계, 구...",
                    isAcquired = false,
                    placement = "고양시",
                    testTime = "09:00",
                    createdAt = LocalDate.of(2025, 11, 23),
                    level = "IM3"
                ),
                isEditMode = true,
                onEditClick = {},
                onDeleteClick = {}
            )
        }
    }
}
