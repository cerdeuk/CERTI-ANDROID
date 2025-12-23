package org.sopt.certi.presentation.ui.mycertification

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import org.sopt.certi.R
import org.sopt.certi.core.component.header.MyPageHeader
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.domain.model.certification.CertificationData
import org.sopt.certi.presentation.ui.mycertification.component.MyCertItem
import org.sopt.certi.ui.theme.CERTITheme

@Composable
fun EditCertRoute(
    padding: PaddingValues,
    viewModel: MyCertViewModel = hiltViewModel()
) {
}

@Composable
fun EditCertScreen(
    certifications: ImmutableList<CertificationData>,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        MyPageHeader(
            headerTitle = stringResource(R.string.edit_certification),
            modifier = Modifier.padding(vertical = screenWidthDp(24.dp))
        )

        LazyColumn(
            contentPadding = PaddingValues(horizontal = screenWidthDp(20.dp)),
            verticalArrangement = Arrangement.spacedBy(screenWidthDp(16.dp))
        ) {
            items(
                items = certifications,
                key = { it.certificationId }
            ) { certification ->
                MyCertItem(
                    certificationData = certification,
                    isEditClick = {},
                    isDeleteClick = {}
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun EditCertPreview() {
    CERTITheme {
        EditCertScreen(
            certifications = dummyCertifications.toImmutableList()
        )
    }
}
