package org.sopt.certi.core.component.section

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.certi.R
import org.sopt.certi.core.util.noRippleClickable
import org.sopt.certi.domain.model.CertificationListData
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun CertificationListSection(
    certificationListData: CertificationListData,
    onLikeClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = CertiTheme.colors.gray100,
                shape = RoundedCornerShape(12.dp)
            )
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_star_24),
            contentDescription = stringResource(R.string.textfield_placeholder),
            tint = if (certificationListData.isLiked) CertiTheme.colors.subYellow else CertiTheme.colors.gray200,
            modifier = Modifier
                .noRippleClickable(onLikeClick)
                .align(Alignment.TopEnd)
                .padding(12.dp)
                .size(24.dp)
        )

        Column(
            modifier = Modifier.padding(start = 12.dp, top = 22.dp, bottom = 16.dp)
        ){

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
            onLikeClick = {isLiked = !isLiked}
        )
    }
}