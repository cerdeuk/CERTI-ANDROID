package org.sopt.certi.presentation.ui.editacademicinfo.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import org.sopt.certi.R
import org.sopt.certi.core.component.button.CertiBasicButton
import org.sopt.certi.core.util.roundedBackgroundWithBorder
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.domain.type.CategoryType
import org.sopt.certi.ui.theme.CertiTheme

private const val MAX_SELECTION_COUNT = 3

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectJobCategoryBottomSheet(
    sheetState: SheetState,
    selectedList: List<CategoryType>,
    onItemClick: (CategoryType) -> Unit,
    onConfirmClick: () -> Unit,
    changeBottomSheetVisibility: (Boolean) -> Unit = { }
) {
    val coroutineScope = rememberCoroutineScope()

    ModalBottomSheet(
        onDismissRequest = { changeBottomSheetVisibility(false) },
        shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp),
        containerColor = CertiTheme.colors.white,
        sheetState = sheetState,
        dragHandle = {
            Box(
                modifier = Modifier
                    .padding(top = screenHeightDp(20.dp), bottom = screenHeightDp(48.dp))
                    .width(screenWidthDp(80.dp))
                    .height(5.dp)
                    .roundedBackgroundWithBorder(12.dp, CertiTheme.colors.gray200)
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = screenWidthDp(20.dp))
        ) {
            Text(
                text = stringResource(id = R.string.job_category_bottomsheet_title),
                style = CertiTheme.typography.subtitle.bold_20,
                color = CertiTheme.colors.black,
                modifier = Modifier.padding(bottom = screenHeightDp(12.dp))
            )

            Text(
                text = stringResource(id = R.string.job_category_bottomsheet_sub_title),
                style = CertiTheme.typography.caption.semibold_14,
                color = CertiTheme.colors.mainBlue,
                modifier = Modifier.padding(bottom = screenHeightDp(4.dp))
            )

            Text(
                text = stringResource(id = R.string.job_category_bottomsheet_content),
                style = CertiTheme.typography.caption.regular_12,
                color = CertiTheme.colors.gray500,
                modifier = Modifier.padding(bottom = screenHeightDp(10.dp))
            )

            HorizontalDivider(thickness = 1.dp, color = CertiTheme.colors.gray200)

            LazyVerticalGrid(
                columns = GridCells.Fixed(MAX_SELECTION_COUNT),
                userScrollEnabled = false,
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.padding(top = screenHeightDp(36.dp), bottom = screenHeightDp(32.dp))
            ) {
                items(CategoryType.entries) { category ->
                    val selectedIndex = selectedList.indexOf(category)
                    val isSelected = selectedIndex != -1

                    BottomSheetButton(
                        categoryType = category,
                        isSelected = isSelected,
                        selectNumber = if (isSelected) selectedIndex + 1 else 0,
                        clickable = isSelected || selectedList.size < MAX_SELECTION_COUNT,
                        onClick = onItemClick
                    )
                }
            }

            CertiBasicButton(
                modifier = Modifier
                    .padding(bottom = screenHeightDp(36.dp))
                    .fillMaxWidth(),
                buttonText = stringResource(R.string.job_category_bottomsheet_confirm_button),
                enabled = selectedList.isNotEmpty(),
                onClick = {
                    coroutineScope.launch {
                        sheetState.hide()
                        changeBottomSheetVisibility(false)
                        onConfirmClick()
                    }
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun SelectJobCategoryBottomSheetWithClickPreview() {
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    var showBottomSheet by remember { mutableStateOf(false) }

    val selectedList = remember { mutableStateListOf<CategoryType>() }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable {
                showBottomSheet = true
            }
    ) {
        if (showBottomSheet) {
            SelectJobCategoryBottomSheet(
                sheetState = sheetState,
                selectedList = selectedList,
                onItemClick = { categoryType ->
                    if (selectedList.contains(categoryType)) {
                        selectedList.remove(categoryType)
                    } else {
                        selectedList.add(categoryType)
                    }
                },
                changeBottomSheetVisibility = { showBottomSheet = it },
                onConfirmClick = { }
            )
        }
    }
}
