package org.sopt.certi.presentation.ui.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import org.sopt.certi.R
import org.sopt.certi.core.component.button.CertiBasicButton
import org.sopt.certi.core.component.textfield.CertiBasicTextField
import org.sopt.certi.core.state.UiState
import org.sopt.certi.core.util.noRippleClickable
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.core.util.showIf
import org.sopt.certi.core.util.widthForScreenPercentage
import org.sopt.certi.presentation.ui.onboarding.state.OnBoardingUnivUiState
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun OnBoardingUnivRoute(
    padding: PaddingValues,
    navigateToGrade: () -> Unit,
    viewModel: OnBoardingViewModel
) {
    val uiState by viewModel.onBoardingUnivUiState.collectAsStateWithLifecycle()

    OnBoardingUnivScreen(
        onBoardingUnivUiState = uiState,
        onValueChange = viewModel::onUnivSearchTextChange,
        onSearchClick = { viewModel.getUnivList(uiState.univSearchText) },
        onUnivSelected = viewModel::selectUniv,
        univList = (uiState.univListLoadState as? UiState.Success)?.data.orEmpty().toImmutableList(),
        navigateToGrade = navigateToGrade,
        modifier = Modifier.padding(padding)
    )
}

@Composable
fun OnBoardingUnivScreen(
    onBoardingUnivUiState: OnBoardingUnivUiState,
    onValueChange: (String) -> Unit,
    onSearchClick: () -> Unit,
    onUnivSelected: (String) -> Unit,
    univList: ImmutableList<String>,
    navigateToGrade: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(top = screenHeightDp(50.dp), bottom = screenHeightDp(22.dp), start = screenWidthDp(20.dp), end = screenWidthDp(20.dp))
    ) {
        Column {
            Image(
                painter = painterResource(id = R.drawable.img_onboarding_progressbar_1),
                contentDescription = null,
                modifier = Modifier
                    .widthForScreenPercentage(200.dp),
                contentScale = ContentScale.FillWidth
            )
            Spacer(modifier = Modifier.padding(top = screenHeightDp(40.dp)))

            Text(
                text = stringResource(R.string.onboarding_univ_title),
                style = CertiTheme.typography.subtitle.bold_20,
                color = CertiTheme.colors.gray600
            )
            Spacer(modifier = Modifier.padding(top = screenHeightDp(38.dp)))

            OnboardingUnivSection(
                value = onBoardingUnivUiState.univSearchText,
                onValueChange = onValueChange,
                onSearchClick = onSearchClick
            )

            when (onBoardingUnivUiState.loadState) {
                is UiState.Init -> {}
                is UiState.Success -> {
                    LazyColumn(
                        modifier = Modifier
                            .showIf(
                                condition = onBoardingUnivUiState.univListLoadState is UiState.Success
                            )
                            .padding(bottom = screenHeightDp(60.dp))
                    ) {
                        items(univList.size) { univ ->
                            Column(
                                modifier = Modifier.noRippleClickable { onUnivSelected(univList[univ]) }
                            ) {
                                Text(
                                    text = univList[univ],
                                    style = CertiTheme.typography.body.regular_16,
                                    color = CertiTheme.colors.black,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = screenWidthDp(20.dp), vertical = screenHeightDp(20.dp))
                                )
                                HorizontalDivider(
                                    color = CertiTheme.colors.gray100,
                                    thickness = screenHeightDp(1.dp)
                                )
                            }
                        }
                    }
                }

                is UiState.Empty -> {}
                is UiState.Failure -> {}
                is UiState.Loading -> {}
            }
        }

        CertiBasicButton(
            buttonText = stringResource(R.string.button_next),
            onClick = navigateToGrade,
            modifier = Modifier
                .fillMaxWidth()
                .align(alignment = Alignment.BottomCenter),
            enabled = (onBoardingUnivUiState.univSearchText.isNotBlank() && univList.contains(onBoardingUnivUiState.univSearchText))
        )
    }
}

@Composable
private fun OnboardingUnivSection(
    value: String,
    onValueChange: (String) -> Unit,
    onSearchClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    CertiBasicTextField(
        value = value,
        onValueChange = onValueChange,
        onSearchClick = onSearchClick,
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewOnBoardingUnivScreen() {
    CERTITheme {

    }
}
