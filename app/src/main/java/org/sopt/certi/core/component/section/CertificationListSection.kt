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
import org.sopt.certi.domain.model.ResumeData
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun CertificationListSection(
    certificationListData: ResumeData,
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
            tint = if (certificationListData.isFavorite) CertiTheme.colors.subYellow else CertiTheme.colors.gray200,
            modifier = Modifier
                .noRippleClickable(onLikeClick)
                .align(Alignment.TopEnd)
                .padding(screenWidthDp(12.dp))
                .size(screenWidthDp(22.dp))
        )

        Column(
            modifier = Modifier.padding(start = screenWidthDp(12.dp), top = screenHeightDp(22.dp), bottom = screenHeightDp(16.dp))
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
                    text = certificationListData.agencyName,
                    style = CertiTheme.typography.caption.regular_12,
                    color = CertiTheme.colors.black,
                    modifier = Modifier.padding(start = screenWidthDp(8.dp))
                )
            }
            Spacer(Modifier.height(screenHeightDp(12.dp)))

            CertiChipList(categories = certificationListData.tags)
        }

        Row(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = screenWidthDp(14.dp), bottom = screenHeightDp(18.dp)),
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
                modifier = Modifier.padding(start = screenWidthDp(4.dp))
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
            certificationListData = ResumeData(
                certificationId = 1,
                isFavorite = isLiked,
                certificationName = "정보처리기사",
                agencyName = "국가기술자격",
                tags = listOf("컴퓨터공학", "시각디자인", "경영")
            ),
            onLikeClick = { isLiked = !isLiked },
            onCertificationClick = {}
        )
    }
}
