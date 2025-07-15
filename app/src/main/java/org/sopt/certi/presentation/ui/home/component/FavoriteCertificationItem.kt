package org.sopt.certi.presentation.ui.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.certi.R
import org.sopt.certi.core.util.heightForScreenPercentage
import org.sopt.certi.core.util.noRippleClickable
import org.sopt.certi.core.util.roundedBackgroundWithBorder
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.core.util.widthForScreenPercentage
import org.sopt.certi.domain.model.CertificationData
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun FavoriteCertificationListSection(
    favoriteCertificationList: List<CertificationData>,
    onFavoriteClicked: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = screenWidthDp(20.dp)),
        horizontalArrangement = Arrangement.spacedBy(screenWidthDp(12.dp)),
        modifier = modifier
            .fillMaxWidth()
    ) {
        items(favoriteCertificationList) { item ->
            FavoriteCertificationItem(
                favoriteCertificationData = item,
                onFavoriteClicked = onFavoriteClicked
            )
        }
    }
}

@Composable
fun FavoriteCertificationItem(
    favoriteCertificationData: CertificationData,
    onFavoriteClicked: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .widthForScreenPercentage(160.dp)
            .heightForScreenPercentage(160.dp)
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
                .padding(horizontal = 14.dp, vertical = 20.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = favoriteCertificationData.certificationType,
                    style = CertiTheme.typography.caption.regular_14,
                    color = CertiTheme.colors.gray600
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_star_24),
                    contentDescription = null,
                    tint = if (favoriteCertificationData.isFavorite) CertiTheme.colors.subYellow else CertiTheme.colors.gray100,
                    modifier = Modifier
                        .width(screenWidthDp(24.dp))
                        .height(screenHeightDp(24.dp))
                        .noRippleClickable { onFavoriteClicked(favoriteCertificationData.certificationId) }
                )
            }
            Spacer(modifier = Modifier.height(8.dp))

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
                .padding(horizontal = 14.dp, vertical = 20.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_paper_16),
                    contentDescription = null,
                    tint = CertiTheme.colors.gray400,
                    modifier = Modifier
                        .width(screenWidthDp(16.dp))
                        .height(screenHeightDp(16.dp))
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
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_certification_16),
                    contentDescription = null,
                    tint = CertiTheme.colors.gray400,
                    modifier = Modifier
                        .width(screenWidthDp(16.dp))
                        .height(screenHeightDp(16.dp))
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
}
