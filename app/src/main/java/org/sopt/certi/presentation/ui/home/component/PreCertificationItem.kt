package org.sopt.certi.presentation.ui.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.Icon
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import org.sopt.certi.R
import org.sopt.certi.domain.model.PreCertificationData
import org.sopt.certi.ui.theme.CertiTheme
import androidx.compose.foundation.layout.size
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import org.sopt.certi.presentation.type.CertiEmojiType

@Composable
fun PreCertificationItem(
    preCertificationData: PreCertificationData,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .background(color = CertiTheme.colors.white),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = CertiTheme.colors.white
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 12.dp, vertical = 14.dp)
        ) {
            Text(
                text = preCertificationData.certificationName,
                style = CertiTheme.typography.body.semibold_16,
                color = CertiTheme.colors.gray600
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = stringResource(R.string.home_average_period, preCertificationData.averagePeriod),
                style = CertiTheme.typography.caption.semibold_12,
                color = CertiTheme.colors.gray600
            )
            Spacer(modifier = Modifier.height(8.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(18.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(vertical = 6.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_date_16),
                            contentDescription = null,
                            tint = CertiTheme.colors.gray400,
                            modifier = Modifier.size(16.dp)
                        )
                        Text(
                            text = preCertificationData.testDate,
                            style = CertiTheme.typography.caption.regular_12,
                            color = CertiTheme.colors.gray500
                        )
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_certification_16),
                            contentDescription = null,
                            tint = CertiTheme.colors.gray400,
                            modifier = Modifier.size(16.dp)
                        )
                        Text(
                            text = preCertificationData.agencyName,
                            style = CertiTheme.typography.caption.regular_12,
                            color = CertiTheme.colors.gray500
                        )
                    }
                }
                Image(
                    painter = painterResource(
                        id = CertiEmojiType.fromIndex(preCertificationData.emojiIndex).resId
                    ),
                    contentDescription = null,
                    modifier = Modifier.size(50.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreCertificationItemPreview1() {
    PreCertificationItem(
        preCertificationData = PreCertificationData(
            certificationId = 1,
            certificationName = "시각디자인산업기사",
            averagePeriod = "3개월",
            testDate = "2025.08.12",
            agencyName = "한국산업인력공단",
            emojiIndex = 0
        ),
        modifier = Modifier.padding(16.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun PreCertificationItemPreview2() {
    PreCertificationItem(
        preCertificationData = PreCertificationData(
            certificationId = 1,
            certificationName = "시각디자인산업기사",
            averagePeriod = "3개월",
            testDate = "2025.08.12",
            agencyName = "한국산업인력공단",
            emojiIndex = 1
        ),
        modifier = Modifier.padding(16.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun PreCertificationItemPreview3() {
    PreCertificationItem(
        preCertificationData = PreCertificationData(
            certificationId = 1,
            certificationName = "시각디자인산업기사",
            averagePeriod = "3개월",
            testDate = "2025.08.12",
            agencyName = "한국산업인력공단",
            emojiIndex = 2
        ),
        modifier = Modifier.padding(16.dp)
    )
}
