package org.sopt.certi.core.component.section

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.certi.R
import org.sopt.certi.core.component.chip.CertiChipList
import org.sopt.certi.core.util.noRippleClickable
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.domain.model.CertificationListData
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun CertificationListSection(
    certificationListData: CertificationListData,
    onLikeClick: () -> Unit,
    onCertificationClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .noRippleClickable(onCertificationClick)
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = CertiTheme.colors.gray100,
                shape = RoundedCornerShape(12.dp)
            )
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_star_24),
            contentDescription = null,
            tint = if (certificationListData.isLiked) CertiTheme.colors.subYellow else CertiTheme.colors.gray200,
            modifier = Modifier
                .noRippleClickable(onLikeClick)
                .align(Alignment.TopEnd)
                .padding(screenWidthDp(0.03f))
                .size(screenWidthDp(0.06f))
        )

        Column(
            modifier = Modifier.padding(start = screenWidthDp(0.03f), top = screenHeightDp(0.028f), bottom = screenHeightDp(0.02f))
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = certificationListData.certificationName,
                    style = CertiTheme.typography.body.semibold_18,
                    color = CertiTheme.colors.black
                )

                Text(
                    text = certificationListData.agency,
                    style = CertiTheme.typography.caption.regular_12,
                    color = CertiTheme.colors.black,
                    modifier = Modifier.padding(start = screenWidthDp(0.02f))
                )
            }
            Spacer(Modifier.height(screenHeightDp(0.015f)))

            CertiChipList(categories = certificationListData.categories)
        }

        Row(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = screenWidthDp(0.04f), bottom = screenHeightDp(0.023f)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_paper_16),
                contentDescription = null,
                tint = CertiTheme.colors.gray400,
                modifier = Modifier.size(16.dp)
            )

            Text(
                text = certificationListData.applicationMethod,
                style = CertiTheme.typography.caption.regular_12,
                color = CertiTheme.colors.gray500,
                modifier = Modifier.padding(start = screenWidthDp(0.01f))
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewCertificationListSection() {
    var isLiked by remember { mutableStateOf(false) }

    CERTITheme {
        CertificationListSection(
            certificationListData = CertificationListData(
                certificationId = 1,
                isLiked = isLiked,
                certificationName = "정보처리기사",
                agency = "국가기술자격",
                categories = listOf("컴퓨터공학", "시각디자인", "경영"),
                applicationMethod = "실기형"
            ),
            onLikeClick = { isLiked = !isLiked },
            onCertificationClick = {}
        )
    }
}
