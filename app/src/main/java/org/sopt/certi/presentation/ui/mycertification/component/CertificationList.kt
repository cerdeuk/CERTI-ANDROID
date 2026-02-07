package org.sopt.certi.presentation.ui.mycertification.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.ImmutableList
import org.sopt.certi.R
import org.sopt.certi.core.component.section.MyCertificationListItemSection
import org.sopt.certi.core.util.noRippleClickable
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.domain.model.certification.CertificationData
import org.sopt.certi.ui.theme.CertiTheme

private enum class CertListType {
    HEADER,
    ITEM
}

private const val KEY_EDIT_BUTTON = "key_edit_button"

@Composable
fun CertificationList(
    isEditMode: Boolean,
    certifications: ImmutableList<CertificationData>,
    onEditModeToggle: () -> Unit,
    onCertificationClick: (Long) -> Unit,
    onEditClick: (Long) -> Unit,
    onDeleteClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    val listState = rememberLazyListState()

    LaunchedEffect(isEditMode) {
        listState.scrollToItem(0)
    }

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        state = listState,
        contentPadding = PaddingValues(
            start = screenWidthDp(20.dp),
            top = screenHeightDp(16.dp),
            end = screenWidthDp(20.dp),
            bottom = screenHeightDp(20.dp)
        ),
        verticalArrangement = Arrangement.spacedBy(screenWidthDp(16.dp))
    ) {
        if (!isEditMode && certifications.isNotEmpty()) {
            item(
                key = KEY_EDIT_BUTTON,
                contentType = CertListType.HEADER
            ) {
                Text(
                    text = stringResource(R.string.edit),
                    style = CertiTheme.typography.body.semibold_16,
                    color = CertiTheme.colors.gray400,
                    textAlign = TextAlign.End,
                    modifier = Modifier
                        .fillMaxWidth()
                        .noRippleClickable(onEditModeToggle)
                )
            }
        }

        items(
            items = certifications,
            key = { it.certificationId },
            contentType = { CertListType.ITEM }
        ) { certification ->
            MyCertificationListItemSection(
                certificationData = certification,
                isEditMode = isEditMode,
                onCertificationClick = onCertificationClick,
                onModifyClick = onEditClick,
                onDeleteClick = onDeleteClick
            )
        }
    }
}
