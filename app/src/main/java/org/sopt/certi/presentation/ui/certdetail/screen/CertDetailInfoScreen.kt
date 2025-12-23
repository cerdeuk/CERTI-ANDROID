package org.sopt.certi.presentation.ui.certdetail.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.certi.R
import org.sopt.certi.core.component.chip.CertiDefaultChip
import org.sopt.certi.core.util.heightForScreenPercentage
import org.sopt.certi.core.util.roundedBackgroundWithBorder
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.core.util.widthForScreenPercentage
import org.sopt.certi.domain.model.certification.CertificationData
import org.sopt.certi.presentation.type.AcquireButtonType
import org.sopt.certi.presentation.ui.certdetail.component.button.AcquireButton
import org.sopt.certi.presentation.ui.certdetail.component.button.MoveToWebButton
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun CertDetailInfoScreen(
    certData: CertificationData,
    showWebView: () -> Unit = {},
    acquireExpectCert: () -> Unit = {},
    acquiredCert: () -> Unit = {}
) {
    LazyColumn(
        contentPadding = PaddingValues(top = screenHeightDp(36.dp), bottom = screenHeightDp(36.dp))
    ) {
        item {
            Text(
                text = certData.certificationName,
                style = CertiTheme.typography.subtitle.bold_20,
                color = CertiTheme.colors.gray600
            )

            Spacer(Modifier.heightForScreenPercentage(12.dp))

            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(screenWidthDp(8.dp))
            ) {
                items(certData.tags.size) { index ->
                    CertiDefaultChip(certData.tags[index])
                }
            }

            Spacer(Modifier.heightForScreenPercentage(36.dp))
        }

        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .roundedBackgroundWithBorder(
                        cornerRadius = 12.dp,
                        backgroundColor = CertiTheme.colors.blueWhite,
                        borderColor = CertiTheme.colors.lightBlue
                    )
                    .padding(vertical = screenHeightDp(20.dp), horizontal = screenWidthDp(22.dp))
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(40.dp)
                ) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = stringResource(R.string.cert_detail_average_period_title),
                            style = CertiTheme.typography.body.semibold_16,
                            color = CertiTheme.colors.gray600
                        )
                        Spacer(Modifier.weight(1f))
                        Text(
                            text = certData.averagePeriod,
                            style = CertiTheme.typography.body.regular_16,
                            color = CertiTheme.colors.gray600
                        )
                    }

                    Row(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = stringResource(R.string.cert_detail_charge_title),
                            style = CertiTheme.typography.body.semibold_16,
                            color = CertiTheme.colors.gray600
                        )
                        Spacer(Modifier.weight(1f))
                        Text(
                            text = certData.charge,
                            style = CertiTheme.typography.body.regular_16,
                            color = CertiTheme.colors.gray600
                        )
                    }

                    Row(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = stringResource(R.string.cert_detail_agency_title),
                            style = CertiTheme.typography.body.semibold_16,
                            color = CertiTheme.colors.gray600
                        )
                        Spacer(Modifier.weight(1f))
                        Text(
                            text = certData.agencyName,
                            style = CertiTheme.typography.body.regular_16,
                            color = CertiTheme.colors.gray600
                        )
                    }
                }
            }

            Spacer(Modifier.heightForScreenPercentage(48.dp))
        }

        item {
            Text(
                text = stringResource(R.string.cert_detail_description_title),
                style = CertiTheme.typography.body.bold_18,
                color = CertiTheme.colors.gray600
            )

            Spacer(Modifier.heightForScreenPercentage(36.dp))

            Text(
                text = certData.testType,
                style = CertiTheme.typography.body.semibold_16,
                color = CertiTheme.colors.gray600
            )

            Spacer(Modifier.heightForScreenPercentage(12.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .roundedBackgroundWithBorder(
                        cornerRadius = 12.dp,
                        backgroundColor = CertiTheme.colors.white,
                        borderColor = CertiTheme.colors.gray100
                    )
                    .padding(vertical = screenHeightDp(20.dp), horizontal = screenWidthDp(20.dp))
            ) {
                Text(
                    text = certData.description,
                    style = CertiTheme.typography.caption.regular_14,
                    color = CertiTheme.colors.gray600
                )
            }

            Spacer(Modifier.heightForScreenPercentage(24.dp))

            Text(
                text = stringResource(R.string.cert_detail_test_date_title),
                style = CertiTheme.typography.body.semibold_16,
                color = CertiTheme.colors.gray600
            )

            Spacer(Modifier.heightForScreenPercentage(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_date_16),
                    contentDescription = null,
                    tint = Color.Unspecified
                )

                Spacer(Modifier.widthForScreenPercentage(6.dp))

                Text(
                    text = certData.testDateInformation,
                    style = CertiTheme.typography.body.regular_16,
                    color = CertiTheme.colors.gray600
                )
            }

            Spacer(Modifier.heightForScreenPercentage(24.dp))

            Text(
                text = stringResource(R.string.cert_detail_application_method_title),
                style = CertiTheme.typography.body.semibold_16,
                color = CertiTheme.colors.gray600
            )

            Spacer(Modifier.heightForScreenPercentage(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_certification_16),
                    contentDescription = null,
                    tint = Color.Unspecified
                )

                Spacer(Modifier.widthForScreenPercentage(6.dp))

                Text(
                    text = certData.applicationMethod,
                    style = CertiTheme.typography.body.regular_16,
                    color = CertiTheme.colors.gray600
                )
            }

            Spacer(Modifier.heightForScreenPercentage(24.dp))

            Text(
                text = stringResource(R.string.cert_detail_expiration_period_title),
                style = CertiTheme.typography.body.semibold_16,
                color = CertiTheme.colors.gray600
            )

            Spacer(Modifier.heightForScreenPercentage(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_clock_16),
                    contentDescription = null,
                    tint = Color.Unspecified
                )

                Spacer(Modifier.widthForScreenPercentage(6.dp))

                Text(
                    text = certData.expirationPeriod,
                    style = CertiTheme.typography.body.regular_16,
                    color = CertiTheme.colors.gray600
                )
            }

            Spacer(Modifier.heightForScreenPercentage(24.dp))
        }

        item {
            MoveToWebButton {
                showWebView()
            }
            Spacer(Modifier.heightForScreenPercentage(76.dp))
        }

        item {
            AcquireButton(
                acquireButtonType = AcquireButtonType.EXPECTED,
                onClick = {
                    acquireExpectCert()
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.heightForScreenPercentage(12.dp))

            AcquireButton(
                acquireButtonType = AcquireButtonType.FINISH,
                onClick = {
                    acquiredCert()
                },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewCertDetailInfoScreen() {
    val dummyCertData = CertificationData(
        certificationId = 0,
        certificationName = "GTQ 1급 (그래픽기술자격)",
        averagePeriod = "100일",
        agencyName = "국가기술자격",
        testType = "실기형",
        tags = listOf("aa", "bb", "cc"),
        charge = "12000",
        description = "2D 그래픽 툴의 기능을 활용한 사고의 시각화를 통해 이미지 제작, 수정, 편집 및 그래픽 디자인을 창출하는 업무를 수행하고 이를 통해 비지니스 커뮤니케이션을 원활하게 한다. 1급과 2급, 급수의 차이는 이 업무를 수행하는 툴 활용 능력의 범위와 숙련도 등의 고도화 차이이다.",
        testDateInformation = "매월 넷째주 토요일 정기시험 시행 (총 12회)",
        applicationMethod = "온라인(한국생산성본부 홈페이지)",
        applicationUrl = "asdf",
        expirationPeriod = "1년"
    )

    CERTITheme {
        CertDetailInfoScreen(
            certData = dummyCertData
        )
    }
}
