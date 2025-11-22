package org.sopt.certi.presentation.ui.certlist.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import org.sopt.certi.R
import org.sopt.certi.core.util.noRippleClickable
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.domain.model.certification.CertificationData
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun Top3CertificationItem(
    top3List: ImmutableList<CertificationData>,
    navigateToCertDetail: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        top3List.forEachIndexed { index, item ->
            Row(
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .border(width = 1.dp, color = CertiTheme.colors.gray100, shape = RoundedCornerShape(16.dp))
                    .padding(horizontal = screenWidthDp(24.dp), vertical = screenHeightDp(16.dp))
                    .fillMaxWidth()
                    .noRippleClickable {
                        navigateToCertDetail(item.certificationId)
                    },
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.cert_list_recommend_certification_rank, index + 1),
                    style = CertiTheme.typography.body.bold_18,
                    color = CertiTheme.colors.gray500
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = item.certificationName,
                        style = CertiTheme.typography.body.semibold_18,
                        color = CertiTheme.colors.black,
                        modifier = Modifier.padding(end = screenWidthDp(8.dp))
                    )

                    Text(
                        text = item.agencyName,
                        style = CertiTheme.typography.caption.regular_12,
                        color = CertiTheme.colors.gray500
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewTop3CertificationItem() {
    CERTITheme {
        var certificationList by remember {
            mutableStateOf(
                listOf(
                    CertificationData(
                        certificationId = 1,
                        certificationName = "정보처리기사",
                        tags = listOf("시각디자인", "컴퓨터공학", "경영"),
                        isFavorite = true,
                        testType = "실기형",
                        recommendScore = 20,
                        agencyName = "국가기술자격",
                        description = "자격증 설명입니다. 자격증 설명입니다. 자격증 설명입니다. 자격증 설명입니다.자격증 설명입니다. 자격증 설명입니다. 자격증 설명입니다. 자격증 설명입니다.자격증 설명입니다. 자격증 설명입니다. 자격증 설명입니다. 자격증 설명입니다."
                    ),
                    CertificationData(
                        certificationId = 2,
                        certificationName = "GTQ 1급 (그래픽 기술 자격)",
                        tags = listOf("시각디자인", "컴퓨터공학", "경영"),
                        isFavorite = false,
                        testType = "실기형",
                        recommendScore = 90,
                        agencyName = "국가기술자격",
                        description = "자격증 설명입니다. 자격증 설명입니다. 자격증 설명입니다. 자격증 설명입니다.자격증 설명입니다. 자격증 설명입니다. 자격증 설명입니다. 자격증 설명입니다.자격증 설명입니다. 자격증 설명입니다. 자격증 설명입니다. 자격증 설명입니다."
                    ),
                    CertificationData(
                        certificationId = 3,
                        certificationName = "TOEIC 900+",
                        tags = listOf("경영", "시각디자인", "컴퓨터공학"),
                        isFavorite = true,
                        testType = "실기형",
                        recommendScore = 80,
                        agencyName = "국가기술자격",
                        description = "자격증 설명입니다. 자격증 설명입니다. 자격증 설명입니다. 자격증 설명입니다.자격증 설명입니다. 자격증 설명입니다. 자격증 설명입니다. 자격증 설명입니다.자격증 설명입니다. 자격증 설명입니다. 자격증 설명입니다. 자격증 설명입니다."
                    )
                ).toImmutableList()
            )
        }

        Top3CertificationItem(
            top3List = certificationList,
            navigateToCertDetail = { }
        )
    }
}
