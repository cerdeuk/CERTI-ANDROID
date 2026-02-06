package org.sopt.certi.core.component.section

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.certi.R
import org.sopt.certi.core.component.chip.CertiEditChip
import org.sopt.certi.core.component.chip.CertiEditChipType
import org.sopt.certi.core.util.heightForScreenPercentage
import org.sopt.certi.core.util.noRippleClickable
import org.sopt.certi.core.util.roundedBackgroundWithBorder
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.core.util.toDateFormat
import org.sopt.certi.core.util.widthForScreenPercentage
import org.sopt.certi.domain.model.certification.CertificationData
import org.sopt.certi.ui.theme.CertiTheme
import java.time.format.DateTimeFormatter

@Composable
fun MyCertificationListItemSection(
    certificationData: CertificationData,
    isEditMode: Boolean,
    onCertificationClick: (Long) -> Unit,
    modifier: Modifier = Modifier,
    onModifyClick: (Long) -> Unit = {},
    onDeleteClick: (Long) -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .roundedBackgroundWithBorder(
                cornerRadius = 12.dp,
                backgroundColor = CertiTheme.colors.white,
                borderColor = CertiTheme.colors.lightPurple,
                borderWidth = 1.dp
            )
            .padding(screenWidthDp(16.dp))
            .noRippleClickable { onCertificationClick(certificationData.certificationId) }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            CertificationStatus(certificationData.isAcquired)

            if (isEditMode) {
                Spacer(modifier = Modifier.weight(1f))
                CertiEditChip(CertiEditChipType.MODIFY) {
                    onModifyClick(certificationData.certificationId)
                }

                Spacer(Modifier.widthForScreenPercentage(12.dp))

                CertiEditChip(CertiEditChipType.DELETE) {
                    onDeleteClick(certificationData.certificationId)
                }
            }
        }

        Spacer(Modifier.heightForScreenPercentage(if (!isEditMode) 8.dp else 4.dp))

        CertItemTitleSection(
            certName = certificationData.certificationName,
            certType = certificationData.certificationType
        )

        Spacer(Modifier.heightForScreenPercentage(4.dp))

        Text(
            text = certificationData.description,
            style = CertiTheme.typography.caption.regular_12,
            color = CertiTheme.colors.gray500,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Spacer(Modifier.heightForScreenPercentage(12.dp))

        Row(
            modifier = Modifier.padding(vertical = screenWidthDp(2.dp)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(screenWidthDp(8.dp))
        ) {
            if (certificationData.isAcquired) {
                CertInfoSection(
                    iconRes = R.drawable.ic_date_16,
                    testInfo = certificationData.acquisitionDate.toDateFormat()
                )
                CertInfoSection(
                    iconRes = R.drawable.ic_level,
                    iconColor = CertiTheme.colors.gray400,
                    testInfo = if (certificationData.grade.isBlank()) stringResource(R.string.acquired_grade_empty_text) else certificationData.grade,
                    textColor = if (certificationData.grade.isBlank()) CertiTheme.colors.gray200 else CertiTheme.colors.black
                )
            } else {
                CertInfoSection(
                    iconRes = R.drawable.ic_placemark,
                    testInfo = certificationData.city
                )
                CertInfoSection(
                    iconRes = R.drawable.ic_time,
                    testInfo = certificationData.testTime.format(DateTimeFormatter.ofPattern("HH:mm"))
                )
            }
        }
    }
}

@Composable
private fun CertificationStatus(acquired: Boolean) {
    Row(
        modifier = Modifier
            .background(color = if (acquired) CertiTheme.colors.mainBlue else CertiTheme.colors.purpleBlue, shape = RoundedCornerShape(100.dp))
            .padding(horizontal = screenWidthDp(6.dp), vertical = screenWidthDp(4.dp)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_progresscheck_white),
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier.widthForScreenPercentage(10.dp)
        )

        Text(
            text = if (acquired) {
                stringResource(R.string.my_certification_acquired)
            } else {
                stringResource(R.string.my_certification_pre)
            },
            style = CertiTheme.typography.caption.regular_10,
            color = CertiTheme.colors.white
        )
    }
}

@Preview
@Composable
private fun PreviewMyCertificationListSection() {
    val testData = CertificationData(
        certificationId = 1,
        certificationName = "정보처리기사",
        certificationType = "국가기술자격",
        description = "savhufhviufhdsuihvfhdishviufhdsivhiusd",
        tags = listOf("컴퓨터공학", "시각디자인", "경영")
    )
    MyCertificationListItemSection(
        certificationData = testData,
        isEditMode = true,
        onCertificationClick = {}
    )
}
