package org.sopt.certi.presentation.ui.myacademicinfo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import org.sopt.certi.R
import org.sopt.certi.core.component.textfield.CertiBasicTextField
import org.sopt.certi.core.state.UiState
import org.sopt.certi.core.util.noRippleClickable
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.core.component.header.MyPageHeader
import org.sopt.certi.presentation.type.AcademicInfoType
import org.sopt.certi.presentation.ui.myacademicinfo.state.EditSearchUiState
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun EditUnivRoute(
    padding: PaddingValues,
    viewModel: AcademicInfoViewModel = hiltViewModel()
) {
    val uiState by viewModel.editUnivUiState.collectAsStateWithLifecycle()

    EditSearchScreen(
        pageTitle = stringResource(R.string.onboarding_univ_title),
        uiState = uiState,
        univList = (uiState.searchListLoadState as? UiState.Success)?.data.orEmpty().toImmutableList(),
        onValueChange = { text -> viewModel.onSearchTextChanged(AcademicInfoType.UNIV, text) },
        onSearchClick = { viewModel.onSearchClick(AcademicInfoType.UNIV) },
        onItemSelected = { item -> viewModel.onItemSelected(AcademicInfoType.UNIV, item) },
        onSaveClick = { viewModel.onSaveClick(AcademicInfoType.UNIV) },
        modifier = Modifier.padding(padding)
    )
}

@Composable
fun EditMajorRoute(
    padding: PaddingValues,
    viewModel: AcademicInfoViewModel = hiltViewModel()
) {
    val uiState by viewModel.editMajorUiState.collectAsStateWithLifecycle()

    EditSearchScreen(
        pageTitle = stringResource(R.string.onboarding_major_title),
        uiState = uiState,
        univList = (uiState.searchListLoadState as? UiState.Success)?.data.orEmpty().toImmutableList(),
        onValueChange = { text -> viewModel.onSearchTextChanged(AcademicInfoType.MAJOR, text) },
        onSearchClick = { viewModel.onSearchClick(AcademicInfoType.MAJOR) },
        onItemSelected = { item -> viewModel.onItemSelected(AcademicInfoType.MAJOR, item) },
        onSaveClick = { viewModel.onSaveClick(AcademicInfoType.MAJOR) },
        modifier = Modifier.padding(padding)
    )
}

@Composable
fun EditSearchScreen(
    pageTitle: String,
    uiState: EditSearchUiState,
    univList: ImmutableList<String>,
    onValueChange: (String) -> Unit,
    onSearchClick: () -> Unit,
    onItemSelected: (String) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = screenWidthDp(20.dp))
    ) {
        MyPageHeader(
            isSaveEnable = uiState.isSaveEnable,
            onSaveClick = onSaveClick,
            modifier = Modifier.padding(vertical = screenWidthDp(20.dp))
        )
        Text(
            text = pageTitle,
            style = CertiTheme.typography.subtitle.bold_20,
            color = CertiTheme.colors.gray600,
            modifier = Modifier.padding(vertical = screenHeightDp(24.dp))
        )
        CertiBasicTextField(
            value = uiState.searchText,
            onValueChange = onValueChange,
            onSearchClick = onSearchClick,
            modifier = Modifier.padding(top = screenHeightDp(14.dp))
        )

        when (uiState.loadState) {
            is UiState.Init -> {}
            is UiState.Success -> {
                LazyColumn(
                    modifier = Modifier.padding(bottom = screenHeightDp(60.dp))
                ) {
                    items(univList) { univ ->
                        Column(
                            modifier = Modifier.noRippleClickable { onItemSelected(univ) }
                        ) {
                            Text(
                                text = univ,
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
}

@Preview(showBackground = true)
@Composable
private fun EditUnivNamePreview() {
    val viewModel = remember { AcademicInfoViewModel() }
    val uiState by viewModel.editUnivUiState.collectAsStateWithLifecycle()

    CERTITheme {
        EditSearchScreen(
            pageTitle = stringResource(R.string.onboarding_univ_title),
            uiState = uiState,
            onValueChange = {},
            onSearchClick = {},
            univList = (uiState.searchListLoadState as? UiState.Success)?.data.orEmpty().toImmutableList(),
            onItemSelected = {},
            onSaveClick = {},
            modifier = Modifier
                .fillMaxSize()
                .background(CertiTheme.colors.white)
                .statusBarsPadding()
        )
    }
}
