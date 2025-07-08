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
import org.sopt.certi.presentation.type.SelectableButtonType
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun OnBoardingSelectableButtons(
    selectableButtonType: SelectableButtonType,
    onOptionSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
    selectedOption: String? = null
) {
    val chunkedOptions = selectableButtonType.options.chunked(size = selectableButtonType.chunkedSize)

    Column {
        chunkedOptions.forEach { rowOptions ->
            Row(
                modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                rowOptions.forEach { option ->
                    OnBoardingSelectableButton(
                        selectableButtonType = selectableButtonType,
                        option = option,
                        onClick = { onOptionSelected(option) },
                        isSelected = option == selectedOption,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
            if (selectableButtonType == SelectableButtonType.CATEGORY) {
                Spacer(modifier = Modifier.height(14.dp))
            } else {
                Spacer(modifier = Modifier.height(12.dp))
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
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .roundedBackgroundWithBorder(
                cornerRadius = 12.dp,
                backgroundColor = if (isSelected) CertiTheme.colors.lightBlue else CertiTheme.colors.blueWhite,
                borderColor = if (isSelected) CertiTheme.colors.skyBlue else CertiTheme.colors.lightBlue
            )
            .noRippleClickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = option,
            modifier = Modifier.padding(vertical = selectableButtonType.verticalPadding.dp),
            style = if (isSelected) CertiTheme.typography.body.semibold_16 else CertiTheme.typography.body.regular_16,
            color = if (isSelected) CertiTheme.colors.gray600 else CertiTheme.colors.gray500
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
            onOptionSelected = { option -> selectedOption = option },
            selectedOption = selectedOption
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
            onOptionSelected = { option -> selectedOption = option },
            selectedOption = selectedOption
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
            onOptionSelected = { option -> selectedOption = option },
            selectedOption = selectedOption
        )
    }
}
