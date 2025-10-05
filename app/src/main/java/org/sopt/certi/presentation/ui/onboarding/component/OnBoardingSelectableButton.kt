package org.sopt.certi.presentation.ui.onboarding.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.certi.core.util.noRippleClickable
import org.sopt.certi.core.util.roundedBackgroundWithBorder
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.presentation.type.SelectableButtonType
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun OnBoardingSelectableButtons(
    selectableButtonType: SelectableButtonType,
    selectedOption: String?,
    onOptionChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
    disabledOptions: List<String> = emptyList()
) {
    val chunkedOptions = selectableButtonType.options.chunked(size = selectableButtonType.chunkedSize)

    Column {
        chunkedOptions.forEach { rowOptions ->
            Row(
                modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(screenWidthDp(14.dp)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                rowOptions.forEach { option ->
                    val isSelected = option == selectedOption
                    val isEnabled = option !in disabledOptions

                    OnBoardingSelectableButton(
                        selectableButtonType = selectableButtonType,
                        option = option,
                        onClick = { if (isEnabled) onOptionChanged(option) },
                        isSelected = isSelected,
                        enabled = isEnabled,
                        modifier = Modifier.weight(1f)
                    )
                }
                repeat(selectableButtonType.chunkedSize - rowOptions.size) {
                    Spacer(Modifier.weight(1f))
                }
            }
            if (selectableButtonType == SelectableButtonType.CATEGORY) {
                Spacer(modifier = Modifier.height(screenHeightDp(14.dp)))
            } else {
                Spacer(modifier = Modifier.height(screenHeightDp(12.dp)))
            }
        }
    }
}

@Composable
private fun OnBoardingSelectableButton(
    selectableButtonType: SelectableButtonType,
    option: String,
    onClick: () -> Unit,
    isSelected: Boolean,
    enabled: Boolean,
    modifier: Modifier = Modifier
) {
    val backgroundColor = when {
        !enabled -> CertiTheme.colors.gray100
        isSelected -> CertiTheme.colors.lightBlue
        else -> CertiTheme.colors.blueWhite
    }
    val borderColor = when {
        !enabled -> CertiTheme.colors.gray200
        isSelected -> CertiTheme.colors.skyBlue
        else -> CertiTheme.colors.lightBlue
    }
    val textColor = when {
        !enabled -> CertiTheme.colors.gray300
        isSelected -> CertiTheme.colors.mainBlue
        else -> CertiTheme.colors.gray500
    }
    val textStyle = if (isSelected && enabled) CertiTheme.typography.body.semibold_16
    else CertiTheme.typography.body.regular_16

    Box(
        modifier = modifier
            .roundedBackgroundWithBorder(
                cornerRadius = 12.dp,
                backgroundColor = backgroundColor,
                borderColor = borderColor
            )
            .then(
                if (enabled) Modifier.noRippleClickable(onClick = onClick)
                else Modifier
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = option,
            modifier = Modifier.padding(vertical = screenHeightDp(selectableButtonType.verticalPadding)),
            style = textStyle,
            color = textColor
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewOnBoardingSelectableButtonCategory() {
    CERTITheme {
        var selectedOption by remember { mutableStateOf("") }

        OnBoardingSelectableButtons(
            selectableButtonType = SelectableButtonType.CATEGORY,
            selectedOption = selectedOption,
            onOptionChanged = { selectedOption = it }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewOnBoardingSelectableButtonTrack() {
    CERTITheme {
        var selectedOption by remember { mutableStateOf("") }
        OnBoardingSelectableButtons(
            selectableButtonType = SelectableButtonType.TRACK,
            selectedOption = selectedOption,
            onOptionChanged = { option -> selectedOption = option }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewOnBoardingSelectableButtonGrade() {
    CERTITheme {
        var selectedOption by remember { mutableStateOf("") }
        OnBoardingSelectableButtons(
            selectableButtonType = SelectableButtonType.GRADE,
            selectedOption = selectedOption,
            onOptionChanged = { option -> selectedOption = option }
        )
    }
}
