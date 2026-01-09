package org.sopt.certi.presentation.ui.mycertification.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import org.sopt.certi.R
import org.sopt.certi.core.component.button.CertiBasicButton
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.domain.model.certification.CertificationData
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterAcquiredInfoBottomSheet(
    sheetState: SheetState,
    modifier: Modifier = Modifier,
    certificationData: CertificationData,
    onDismissClick: () -> Unit = {}
) {
    val scope = rememberCoroutineScope()

    var dateText by remember { mutableStateOf("") }
    var scoreText by remember { mutableStateOf("") }

    ModalBottomSheet(
        onDismissRequest = {
            onDismissClick()
        },
        sheetState = sheetState,
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = screenWidthDp(20.dp))
        ) {
            Text(
                text = stringResource(R.string.edit_acquired_certification_bottomsheet_title),
                style = CertiTheme.typography.body.bold_18,
                color = CertiTheme.colors.gray600
            )

            Text(
                text = certificationData.certificationName,
                style = CertiTheme.typography.caption.semibold_14,
                color = CertiTheme.colors.gray400
            )

            Row {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_check_24),
                    contentDescription = null,
                    tint = CertiTheme.colors.gray500
                )
                Text(
                    text = stringResource(R.string.edit_acquired_date),
                    style = CertiTheme.typography.body.semibold_16,
                    color = CertiTheme.colors.gray600
                )
            }


            CertiBasicButton(
                buttonText = stringResource(R.string.test_info_bottomsheet_confirm),
                enabled = true,
                onClick = {
                    scope.launch { sheetState.hide() }.invokeOnCompletion {
                        if (!sheetState.isVisible) onDismissClick()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun RegisterAcquiredInfoBottomSheetPreview() {
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    CERTITheme {
        RegisterAcquiredInfoBottomSheet(
            sheetState = sheetState,
            certificationData = CertificationData(
                certificationId = 1,
                certificationName = "GTQ 1급 (그래픽기술자격)"
            )
        )
    }
}