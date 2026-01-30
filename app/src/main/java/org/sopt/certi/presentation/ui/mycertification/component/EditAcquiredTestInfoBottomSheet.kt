package org.sopt.certi.presentation.ui.mycertification.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import org.sopt.certi.R
import org.sopt.certi.core.component.button.CertiBasicButton
import org.sopt.certi.core.component.calendar.DatePickerCalendar
import org.sopt.certi.core.component.textfield.DropdownTextField
import org.sopt.certi.core.util.heightForScreenPercentage
import org.sopt.certi.core.util.roundedBackgroundWithBorder
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.core.util.toLocalDateOrNull
import org.sopt.certi.core.util.toLocalizedDate
import org.sopt.certi.core.util.widthForScreenPercentage
import org.sopt.certi.domain.model.certification.CertificationData
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditAcquiredTextInfoBottomSheet(
    sheetState: SheetState,
    certificationData: CertificationData,
    onConfirm: (String, String) -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scope = rememberCoroutineScope()

    var dateText by remember { mutableStateOf(certificationData.acquisitionDate) }
    var scoreText by remember { mutableStateOf(certificationData.grade) }
    var showCalendar by remember { mutableStateOf(false) }

    var isFocused by remember { mutableStateOf(false) }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp),
        containerColor = CertiTheme.colors.white,
        sheetState = sheetState,
        modifier = modifier,
        dragHandle = {
            Box(
                modifier = Modifier
                    .padding(top = screenHeightDp(20.dp))
                    .widthForScreenPercentage(80.dp)
                    .heightForScreenPercentage(5.dp)
                    .roundedBackgroundWithBorder(12.dp, CertiTheme.colors.gray200)
            )
        }
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.heightForScreenPercentage(44.dp))
            Text(
                text = stringResource(R.string.edit_acquired_bottomsheet_title),
                style = CertiTheme.typography.body.bold_18,
                color = CertiTheme.colors.gray600,
                modifier = Modifier.padding(horizontal = screenWidthDp(20.dp))
            )
            Spacer(modifier = Modifier.heightForScreenPercentage(8.dp))
            Text(
                text = certificationData.certificationName,
                style = CertiTheme.typography.caption.semibold_14,
                color = CertiTheme.colors.gray400,
                modifier = Modifier.padding(horizontal = screenWidthDp(20.dp))
            )

            Spacer(
                modifier = Modifier.heightForScreenPercentage(32.dp)
            )
            Row(
                modifier = Modifier.padding(horizontal = screenWidthDp(20.dp)),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(screenWidthDp(4.dp))
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_check_24),
                    contentDescription = null,
                    tint = CertiTheme.colors.gray500
                )
                Text(
                    text = stringResource(R.string.edit_acquired_bottomsheet_date),
                    style = CertiTheme.typography.body.semibold_16,
                    color = CertiTheme.colors.gray600
                )
            }
            Spacer(modifier = Modifier.heightForScreenPercentage(12.dp))
            DropdownTextField(
                value = dateText.toLocalizedDate(),
                placeholder = stringResource(R.string.edit_acquired_bottomsheet_date_placeholder),
                onClick = { showCalendar = !showCalendar },
                modifier = Modifier.padding(horizontal = screenWidthDp(20.dp))
            )

            Box {
                Column(modifier = Modifier.padding(horizontal = screenWidthDp(20.dp))) {
                    Spacer(modifier = Modifier.heightForScreenPercentage(24.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(screenWidthDp(4.dp))
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.ic_check_24),
                            contentDescription = null,
                            tint = CertiTheme.colors.gray500
                        )
                        Text(
                            text = stringResource(R.string.edit_acquired_bottomsheet_score),
                            style = CertiTheme.typography.body.semibold_16,
                            color = CertiTheme.colors.gray600
                        )
                    }
                    Spacer(modifier = Modifier.heightForScreenPercentage(12.dp))
                    BasicTextField(
                        value = scoreText,
                        onValueChange = { scoreText = it },
                        textStyle = CertiTheme.typography.caption.semibold_12.copy(
                            color = CertiTheme.colors.black
                        ),
                        maxLines = 1,
                        singleLine = true,
                        modifier = Modifier.onFocusChanged { focusState ->
                            isFocused = focusState.isFocused
                        },
                        decorationBox = { innerTextField ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(4.dp))
                                    .border(1.dp, CertiTheme.colors.gray200, RoundedCornerShape(4.dp))
                                    .padding(screenWidthDp(12.dp))
                            ) {
                                if (scoreText.isEmpty() && !isFocused) {
                                    Text(
                                        text = stringResource(R.string.edit_acquired_bottomsheet_score_placerholder),
                                        style = CertiTheme.typography.caption.semibold_12,
                                        color = CertiTheme.colors.gray300
                                    )
                                }
                                innerTextField()
                            }
                        }
                    )
                    Spacer(modifier = Modifier.heightForScreenPercentage(72.dp))
                }

                if (showCalendar) {
                    DatePickerCalendar(
                        selectedDate = dateText.toLocalDateOrNull(),
                        onDateSelected = { date ->
                            dateText = date.toString().replace("-", ".")
                            showCalendar = false
                        },
                        modifier = Modifier
                            .padding(top = screenHeightDp(8.dp), bottom = screenHeightDp(44.dp))
                            .padding(horizontal = screenWidthDp(8.dp))
                    )
                }
            }

            CertiBasicButton(
                buttonText = stringResource(R.string.test_info_bottomsheet_confirm),
                enabled = dateText.isNotEmpty(),
                onClick = {
                    scope.launch { sheetState.hide() }.invokeOnCompletion {
                        if (!sheetState.isVisible) onConfirm(dateText, scoreText)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = screenWidthDp(20.dp))

            )
            Spacer(modifier = Modifier.heightForScreenPercentage(24.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun EditAcquiredTextInfoBottomSheetPreview() {
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    CERTITheme {
        EditAcquiredTextInfoBottomSheet(
            sheetState = sheetState,
            certificationData = CertificationData(
                certificationId = 1,
                certificationName = "GTQ 1급 (그래픽기술자격)"
            ),
            onConfirm = { _, _ -> },
            onDismiss = {}
        )
    }
}
