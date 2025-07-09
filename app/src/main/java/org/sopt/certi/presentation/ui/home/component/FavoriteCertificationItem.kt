package org.sopt.certi.presentation.ui.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.certi.R
import org.sopt.certi.core.util.noRippleClickable
import org.sopt.certi.core.util.roundedBackgroundWithBorder
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.domain.model.FavoriteCertificationData
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun FavoriteCertificationItem(
    favoriteCertificationData: FavoriteCertificationData,
    isFavorite: Boolean,
    onFavoriteClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .width(screenWidthDp(0.444f))
            .height(screenHeightDp(0.205f))
            .roundedBackgroundWithBorder(
                cornerRadius = 12.dp,
                backgroundColor = CertiTheme.colors.white,
                borderColor = CertiTheme.colors.gray100,
                borderWidth = 1.dp
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = screenWidthDp(0.038f), vertical = screenHeightDp(0.023f))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = favoriteCertificationData.qualificationType,
                    style = CertiTheme.typography.caption.regular_14,
                    color = CertiTheme.colors.gray600
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_star_24),
                    contentDescription = null,
                    tint = if (isFavorite) CertiTheme.colors.subYellow else CertiTheme.colors.gray100,
                    modifier = Modifier
                        .width(screenWidthDp(0.066f))
                        .height(screenHeightDp(0.03f))
                        .noRippleClickable { onFavoriteClicked() }
                )
            }
            Spacer(modifier = Modifier.height(screenHeightDp(0.01f)))

            Text(
                text = favoriteCertificationData.certificationName,
                style = CertiTheme.typography.body.bold_18,
                color = CertiTheme.colors.gray600,
                maxLines = 2
            )
        }
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(horizontal = screenWidthDp(0.035f), vertical = screenHeightDp(0.025f)),
            verticalArrangement = Arrangement.spacedBy(screenHeightDp(0.007f))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(screenWidthDp(0.011f)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_paper_16),
                    contentDescription = null,
                    tint = CertiTheme.colors.gray400,
                    modifier = Modifier
                        .width(screenWidthDp(0.044f))
                        .height(screenHeightDp(0.02f))
                )
                Text(
                    text = favoriteCertificationData.testType,
                    style = CertiTheme.typography.caption.regular_12,
                    color = CertiTheme.colors.gray500
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(screenWidthDp(0.011f)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_certification_16),
                    contentDescription = null,
                    tint = CertiTheme.colors.gray400,
                    modifier = Modifier
                        .width(screenWidthDp(0.044f))
                        .height(screenHeightDp(0.02f))
                )
                Text(
                    text = favoriteCertificationData.agencyName,
                    style = CertiTheme.typography.caption.regular_12,
                    color = CertiTheme.colors.gray500
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FavoriteCertificationItemPreview() {
    var isFavorite by remember { mutableStateOf(true) }

    FavoriteCertificationItem(
        favoriteCertificationData = FavoriteCertificationData(
            certificationId = 1,
            certificationName = "시각디자인산업기사안녕",
            qualificationType = "국가기술자격",
            testType = "실기형",
            agencyName = "한국산업인력공단"
        ),
        isFavorite = isFavorite,
        onFavoriteClicked = { isFavorite = !isFavorite }
    )
}
