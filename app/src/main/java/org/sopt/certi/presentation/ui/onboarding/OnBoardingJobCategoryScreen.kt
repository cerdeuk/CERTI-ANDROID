package org.sopt.certi.presentation.ui.onboarding

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.sopt.certi.R
import org.sopt.certi.core.component.button.CertiBasicButton
import org.sopt.certi.core.util.noRippleClickable
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.core.util.widthForScreenPercentage
import org.sopt.certi.presentation.type.SelectableButtonType
import org.sopt.certi.presentation.ui.onboarding.component.OnBoardingSelectableButtons
import org.sopt.certi.presentation.ui.onboarding.state.JobCategoryStep
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun OnBoardingJobCategoryRoute(
    padding: PaddingValues,
    popBackStack: () -> Unit,
    navigateToOnBoardingNickName: () -> Unit,
    viewModel: OnBoardingViewModel
) {
    val jobCategory by viewModel.onBoardingJobCategoryUiState.collectAsStateWithLifecycle()

    BackHandler {
        if (jobCategory.step == JobCategoryStep.FIRST) {
            popBackStack()
        } else {
            viewModel.onJobCategoryPrevClicked()
        }
    }

    OnBoardingJobCategoryScreen(
        step = jobCategory.step,
        currentSelection = jobCategory.currentUiSelection(),
        disabledOptions = jobCategory.disabledOptions(),
        onSelect = viewModel::onJobCategorySelected,
        onNextClick = {
            if (jobCategory.step == JobCategoryStep.THIRD) {
                viewModel.onJobCategoryNextClicked()
                navigateToOnBoardingNickName()
            } else {
                viewModel.onJobCategoryNextClicked()
            }
        },
        onSkipClick = {
            viewModel.onJobCategorySkipClicked()
            navigateToOnBoardingNickName()
        },
        modifier = Modifier.padding(padding)
    )
}

@Composable
fun OnBoardingJobCategoryScreen(
    step: JobCategoryStep,
    currentSelection: String?,
    disabledOptions: List<String>,
    onSelect: (String) -> Unit,
    onNextClick: () -> Unit,
    onSkipClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(top = screenHeightDp(50.dp), bottom = screenHeightDp(22.dp), start = screenWidthDp(20.dp), end = screenWidthDp(20.dp))
    ) {
        Column {
            Image(
                painter = painterResource(id = R.drawable.img_onboarding_progressbar_5),
                contentDescription = null,
                modifier = Modifier
                    .widthForScreenPercentage(200.dp),
                contentScale = ContentScale.FillWidth
            )
            Spacer(modifier = Modifier.padding(top = screenHeightDp(40.dp)))

            Text(
                text = stringResource(R.string.onboarding_category_title),
                style = CertiTheme.typography.subtitle.bold_20,
                color = CertiTheme.colors.gray600
            )
            Spacer(modifier = Modifier.padding(top = screenHeightDp(4.dp)))

            Text(
                text = stringResource(R.string.onboarding_category_description),
                style = CertiTheme.typography.caption.regular_12,
                color = CertiTheme.colors.gray400
            )

            Spacer(modifier = Modifier.padding(top = screenHeightDp(18.dp)))

            OnBoardingSelectableButtons(
                selectableButtonType = SelectableButtonType.CATEGORY,
                selectedOption = currentSelection,
                onOptionChanged = onSelect,
                disabledOptions = disabledOptions
            )
        }

        if (step == JobCategoryStep.FIRST) {
            CertiBasicButton(
                buttonText = stringResource(R.string.button_next),
                onClick = onNextClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(alignment = Alignment.BottomCenter),
                enabled = currentSelection != null
            )
        } else {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
            ) {
                Box(
                    modifier = Modifier
                        .background(
                            color = CertiTheme.colors.gray100,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .clip(RoundedCornerShape(12.dp))
                        .noRippleClickable(onClick = onSkipClick)
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(R.string.button_skip),
                        style = CertiTheme.typography.body.semibold_16,
                        color = CertiTheme.colors.gray500,
                        modifier = Modifier.padding(vertical = screenHeightDp(18.dp))
                    )
                }
                Spacer(modifier = Modifier.padding(screenWidthDp(16.dp)))

                CertiBasicButton(
                    buttonText = stringResource(R.string.button_next),
                    onClick = onNextClick,
                    modifier = Modifier.weight(1f),
                    enabled = currentSelection != null
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewOnBoardingJobCategoryScreen() {
    CERTITheme {
        OnBoardingJobCategoryScreen(
            currentSelection = null,
            disabledOptions = listOf(),
            onSelect = {},
            onSkipClick = {},
            onNextClick = {},
            step = JobCategoryStep.FIRST
        )
    }
}
