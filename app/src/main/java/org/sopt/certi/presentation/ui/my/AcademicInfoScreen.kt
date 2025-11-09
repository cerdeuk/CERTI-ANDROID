package org.sopt.certi.presentation.ui.my

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.sopt.certi.R
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.presentation.ui.my.component.JobCategorySection
import org.sopt.certi.presentation.ui.my.component.MySchoolSection
import org.sopt.certi.presentation.ui.my.component.SelectJobCategoryBottomSheet
import org.sopt.certi.presentation.ui.my.state.AcademicInfoUiState
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun AcademicInfoRoute() { }

@Composable
fun AcademicInfoScreen(
    uiState: AcademicInfoUiState,
    onSchoolManageClick: () -> Unit,
    onMajorManageClick: () -> Unit,
    onReselectCategoryClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.my_academic_info_title),
            style = CertiTheme.typography.subtitle.semibold_20,
            color = CertiTheme.colors.gray600,
            modifier = Modifier.padding(vertical = screenHeightDp(22.dp))
        )
        MySchoolSection(
            onSchoolManageClick = onSchoolManageClick,
            onMajorManageClick = onMajorManageClick,
            modifier = Modifier
                .padding(top = screenHeightDp(32.dp), bottom = screenHeightDp(24.dp))
                .padding(horizontal = screenWidthDp(20.dp))
        )
        HorizontalDivider(
            color = CertiTheme.colors.gray100
        )
        JobCategorySection(
            jobCategoryList = uiState.selectedCategoryList,
            onClick = onReselectCategoryClick,
            modifier = Modifier.padding(horizontal = screenWidthDp(20.dp), vertical = screenHeightDp(24.dp))
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
private fun AcademicInfoPreview() {
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    var showBottomSheet by remember { mutableStateOf(false) }
    val viewModel = remember { AcademicInfoViewModel() }
    val uiState by viewModel.academicInfoUiState.collectAsStateWithLifecycle()
    val editingList by viewModel.editingCategoryList.collectAsStateWithLifecycle()

    CERTITheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(CertiTheme.colors.white)
                .statusBarsPadding()
        ) {
            AcademicInfoScreen(
                uiState = uiState,
                onSchoolManageClick = {},
                onMajorManageClick = {},
                onReselectCategoryClick = {
                    viewModel.startEditing()
                    showBottomSheet = !showBottomSheet
                }
            )

            if (showBottomSheet) {
                SelectJobCategoryBottomSheet(
                    sheetState = sheetState,
                    selectedList = editingList,
                    onItemClick = viewModel::editJobCategory,
                    changeBottomSheetVisibility = { showBottomSheet = it },
                    onConfirmClick = viewModel::saveChanges
                )
            }
        }
    }
}
