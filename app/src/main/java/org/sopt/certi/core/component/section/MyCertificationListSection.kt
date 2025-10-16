package org.sopt.certi.core.component.section

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.certi.R
import org.sopt.certi.core.component.chip.CertiEditChip
import org.sopt.certi.core.component.chip.CertiEditChipType
import org.sopt.certi.core.util.heightForScreenPercentage
import org.sopt.certi.core.util.noRippleClickable
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.core.util.widthForScreenPercentage
import org.sopt.certi.domain.model.certification.CertificationData
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun MyCertificationListSection(
    certificationListData: CertificationData,
    isForEdit: Boolean,
    onCertificationClick: () -> Unit,
    onModifyClick: () -> Unit = {},
    onDeleteClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .border(width = 1.dp, color = CertiTheme.colors.lightPurple, shape = RoundedCornerShape(12.dp))
            .background(color = CertiTheme.colors.white, shape = RoundedCornerShape(12.dp))
            .padding(horizontal = screenWidthDp(16.dp), vertical = screenHeightDp(16.dp))
            .noRippleClickable {
                onCertificationClick()
            }
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                CertificationStatusView(certificationListData.isAcquired)

                Spacer(modifier = Modifier.weight(1f))

                if (isForEdit) {
                    CertiEditChip(CertiEditChipType.MODIFY) {
                        onModifyClick()
                    }

                    Spacer(Modifier.widthForScreenPercentage(12.dp))

                    CertiEditChip(CertiEditChipType.DELETE) {
                        onDeleteClick()
                    }
                }
            }

            if (!isForEdit) {
                Spacer(Modifier.heightForScreenPercentage(8.dp))
            }

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = certificationListData.certificationName,
                    style = CertiTheme.typography.subtitle.semibold_20,
                    color = CertiTheme.colors.black
                )

                Spacer(Modifier.widthForScreenPercentage(8.dp))

                Text(
                    text = certificationListData.agencyName,
                    style = CertiTheme.typography.caption.regular_12,
                    color = CertiTheme.colors.black
                )
            }

            Spacer(Modifier.heightForScreenPercentage(4.dp))

            Text(
                text = certificationListData.description,
                style = CertiTheme.typography.caption.regular_12,
                color = CertiTheme.colors.gray500,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(Modifier.heightForScreenPercentage(12.dp))

            if (certificationListData.isAcquired) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_placemark),
                        tint = Color.Unspecified,
                        contentDescription = null,
                        modifier = Modifier
                            .widthForScreenPercentage(16.dp)
                            .heightForScreenPercentage(16.dp)
                    )

                    Spacer(Modifier.widthForScreenPercentage(4.dp))

                    Text(
                        text = certificationListData.placement,
                        style = CertiTheme.typography.caption.regular_14,
                        color = CertiTheme.colors.black
                    )

                    Spacer(Modifier.widthForScreenPercentage(8.dp))

                    Icon(
                        painter = painterResource(id = R.drawable.ic_time),
                        tint = Color.Unspecified,
                        contentDescription = null,
                        modifier = Modifier
                            .widthForScreenPercentage(16.dp)
                            .heightForScreenPercentage(16.dp)
                    )

                    Spacer(Modifier.widthForScreenPercentage(4.dp))

                    Text(
                        text = certificationListData.testTime,
                        style = CertiTheme.typography.caption.regular_14,
                        color = CertiTheme.colors.black
                    )
                }
            } else {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_date_16),
                        tint = Color.Unspecified,
                        contentDescription = null,
                        modifier = Modifier
                            .widthForScreenPercentage(16.dp)
                            .heightForScreenPercentage(16.dp)
                    )

                    Spacer(Modifier.widthForScreenPercentage(4.dp))

                    Text(
                        text = certificationListData.testDateInformation,
                        style = CertiTheme.typography.caption.regular_14,
                        color = CertiTheme.colors.black
                    )

                    Spacer(Modifier.widthForScreenPercentage(8.dp))

                    Icon(
                        painter = painterResource(id = R.drawable.ic_level),
                        tint = Color.Unspecified,
                        contentDescription = null,
                        modifier = Modifier
                            .widthForScreenPercentage(16.dp)
                            .heightForScreenPercentage(16.dp)
                    )

                    Spacer(Modifier.widthForScreenPercentage(4.dp))

                    Text(
                        text = certificationListData.level,
                        style = CertiTheme.typography.caption.regular_14,
                        color = CertiTheme.colors.black
                    )
                }
            }
        }
    }
}

@Composable
private fun CertificationStatusView(acquired: Boolean) {
    Row(
        modifier = Modifier
            .background(color = CertiTheme.colors.purpleBlue, shape = RoundedCornerShape(100.dp))
            .padding(horizontal = screenWidthDp(6.dp))
            .heightForScreenPercentage(20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_progresscheck_white),
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier
                .heightForScreenPercentage(10.dp)
                .widthForScreenPercentage(10.dp)
        )

        Spacer(Modifier.widthForScreenPercentage(4.dp))

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
        agencyName = "국가기술자격",
        description = "savhufhviufhdsuihvfhdishviufhdsivhiusd",
        tags = listOf("컴퓨터공학", "시각디자인", "경영")
    )
    MyCertificationListSection(
        certificationListData = testData,
        isForEdit = false,
        onCertificationClick = {}
    )
}
