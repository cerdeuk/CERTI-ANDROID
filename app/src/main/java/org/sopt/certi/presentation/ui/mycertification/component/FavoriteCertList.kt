package org.sopt.certi.presentation.ui.mycertification.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.ImmutableList
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.domain.model.certification.CertificationData

@Composable
fun FavoriteCertList(
    certifications: ImmutableList<CertificationData>,
    onFavoriteToggle: (Long) -> Unit,
    onCertificationClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            start = screenWidthDp(20.dp),
            top = screenWidthDp(24.dp),
            end = screenWidthDp(20.dp),
            bottom = screenWidthDp(20.dp)
        ),
        verticalArrangement = Arrangement.spacedBy(screenWidthDp(16.dp))
    ) {
        items(
            items = certifications,
            key = { it.certificationId }
        ) { certification ->
            FavoriteCertItem(
                certificationData = certification,
                onCertificationClick = onCertificationClick,
                onFavoriteToggle = onFavoriteToggle
            )
        }
    }
}
