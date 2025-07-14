package org.sopt.certi.presentation.ui.precertificationedit.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Icon
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import org.sopt.certi.R
import org.sopt.certi.ui.theme.CertiTheme
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import org.sopt.certi.core.util.heightForScreenPercentage
import org.sopt.certi.core.util.noRippleClickable
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.core.util.showIf
import org.sopt.certi.core.util.widthForScreenPercentage
import org.sopt.certi.domain.model.CertificationData
import org.sopt.certi.presentation.type.CertiEmojiType

@Composable
fun PreCertificationListSection(
    preCertificationList: List<CertificationData>,
    onDetailClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = screenWidthDp(20.dp)),
        modifier = modifier
            .fillMaxWidth()
    ) {
        items(preCertificationList) { item ->
            PreCertificationItem(
                preCertificationData = item,
                onDetailClick = onDetailClick
            )
        }
    }
}

@Composable
fun PreCertificationItem(
    preCertificationData: CertificationData,
    onDetailClick: (() -> Unit)? = null,
    onDelete: (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(screenWidthDp(12.dp))
    ) {
        Card(
            modifier = modifier
                .then(if (onDetailClick != null) Modifier.noRippleClickable { onDetailClick() } else Modifier),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(
                containerColor = CertiTheme.colors.white
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = screenWidthDp(12.dp), vertical = screenHeightDp(14.dp))
            ) {
                Text(
                    text = preCertificationData.certificationName,
                    style = CertiTheme.typography.body.semibold_16,
                    color = CertiTheme.colors.gray600
                )
                Spacer(modifier = Modifier.height(screenHeightDp(8.dp)))

                Text(
                    text = stringResource(R.string.home_average_period, preCertificationData.averagePeriod),
                    style = CertiTheme.typography.caption.semibold_12,
                    color = CertiTheme.colors.gray600
                )
                Spacer(modifier = Modifier.height(screenHeightDp(8.dp)))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(screenWidthDp(18.dp))
                ) {
                    Column(
                        modifier = Modifier
                            .padding(vertical = screenHeightDp(6.dp)),
                        verticalArrangement = Arrangement.spacedBy(screenHeightDp(6.dp))
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(screenWidthDp(4.dp))
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.ic_date_16),
                                contentDescription = null,
                                tint = CertiTheme.colors.gray400,
                                modifier = Modifier
                                    .widthForScreenPercentage(16.dp)
                                    .heightForScreenPercentage(16.dp)

                            )
                            Text(
                                text = preCertificationData.nearestTestDate,
                                style = CertiTheme.typography.caption.regular_12,
                                color = CertiTheme.colors.gray500
                            )
                        }
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(screenWidthDp(4.dp))
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.ic_certification_16),
                                contentDescription = null,
                                tint = CertiTheme.colors.gray400,
                                modifier = Modifier
                                    .widthForScreenPercentage(16.dp)
                                    .heightForScreenPercentage(16.dp)
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
                            id = CertiEmojiType.fromIndex(preCertificationData.iconIndex).resId
                        ),
                        contentDescription = null,
                        modifier = Modifier
                            .widthForScreenPercentage(50.dp)
                            .heightForScreenPercentage(50.dp)
                    )
                }
            }
        }
        Icon(
            painter = painterResource(id = R.drawable.ic_close_36),
            contentDescription = null,
            tint = CertiTheme.colors.gray300,
            modifier = Modifier
                .widthForScreenPercentage(36.dp)
                .heightForScreenPercentage(36.dp)
                .showIf(onDelete != null)
                .noRippleClickable { onDelete?.invoke() }
        )
    }
}

@Preview(showBackground = true, name = "클릭가능")
@Composable
private fun PreCertificationItemPreview_click() {
    PreCertificationItem(
        preCertificationData = CertificationData(
            certificationId = 1,
            certificationName = "시각디자인산업기사",
            averagePeriod = "3개월",
            nearestTestDate = "2025.08.12",
            agencyName = "한국산업인력공단",
            iconIndex = 0
        ),
        modifier = Modifier.padding(20.dp),
        onDetailClick = {}
    )
}

@Preview(showBackground = true, name = "삭제가능")
@Composable
private fun PreCertificationItemPreview_delete() {
    PreCertificationItem(
        preCertificationData = CertificationData(
            certificationId = 1,
            certificationName = "시각디자인산업기사",
            averagePeriod = "3개월",
            nearestTestDate = "2025.08.12",
            agencyName = "한국산업인력공단",
            iconIndex = 1
        ),
        modifier = Modifier
            .padding(vertical = 20.dp),
        onDelete = {}
    )
}
