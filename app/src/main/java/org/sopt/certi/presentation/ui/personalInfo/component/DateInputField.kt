package org.sopt.certi.presentation.ui.personalInfo.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import kotlinx.coroutines.delay
import org.sopt.certi.R
import org.sopt.certi.core.util.dropShadow
import org.sopt.certi.core.util.heightForScreenPercentage
import org.sopt.certi.core.util.noRippleClickable
import org.sopt.certi.core.util.roundedBackgroundWithBorder
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.domain.model.DateData
import org.sopt.certi.ui.theme.CERTITheme
import org.sopt.certi.ui.theme.CertiTheme
import java.time.LocalDate
import java.time.YearMonth

private const val VISIBLE_DROPBOX_COUNT = 6
private val DROPBOX_ITEM_HEIGHT = 40.dp

@Composable
fun DateInputField(
    label: String,
    value: DateData,
    onValueChange: (DateData) -> Unit,
    inputFieldBackgroundColor: Color,
    modifier: Modifier = Modifier
) {
    val currentDate = remember { LocalDate.now() }
    val yearList = remember(currentDate) {
        (currentDate.year - 100..currentDate.year + 100).map { it.toString() }
    }

    val monthList = remember { (1..12).map { "%02d".format(it) } }

    val daysInMonth = remember(value.year, value.month) {
        if (value.year != null && value.month != null) {
            runCatching { YearMonth.of(value.year, value.month).lengthOfMonth() }.getOrDefault(31)
        } else {
            31
        }
    }
    val dayList = remember(daysInMonth) { (1..daysInMonth).map { "%02d".format(it) } }

    fun updateDate(newYear: Int? = value.year, newMonth: Int? = value.month, newDay: Int? = value.day) {
        var validatedDay = newDay

        if (newYear != null && newMonth != null && validatedDay != null) {
            val maxDay = runCatching { YearMonth.of(newYear, newMonth).lengthOfMonth() }.getOrDefault(31)
            if (validatedDay > maxDay) validatedDay = null
        }
        onValueChange(DateData(newYear, newMonth, validatedDay))
    }

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(screenWidthDp(12.dp))
    ) {
        Text(
            text = label,
            style = CertiTheme.typography.body.semibold_16,
            color = CertiTheme.colors.gray600
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(screenWidthDp(8.dp))
        ) {
            DateDropdown(
                placeholder = "YYYY",
                items = yearList,
                value = value.yearText,
                onValueChange = { updateDate(newYear = it.toIntOrNull()) },
                backgroundColor = inputFieldBackgroundColor,
                initialScrollItem = currentDate.year.toString(),
                modifier = Modifier.widthIn(min = screenWidthDp(94.dp), max = screenWidthDp(114.dp))
            )
            DateDropdown(
                placeholder = "MM",
                items = monthList,
                value = value.monthText,
                onValueChange = { updateDate(newMonth = it.toIntOrNull()) },
                backgroundColor = inputFieldBackgroundColor,
                modifier = Modifier.widthIn(min = screenWidthDp(76.dp), max = screenWidthDp(94.dp))
            )
            DateDropdown(
                placeholder = "DD",
                items = dayList,
                value = value.dayText,
                onValueChange = { updateDate(newDay = it.toIntOrNull()) },
                backgroundColor = inputFieldBackgroundColor,
                modifier = Modifier.widthIn(min = screenWidthDp(76.dp), max = screenWidthDp(94.dp))
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun DateDropdown(
    placeholder: String,
    items: List<String>,
    value: String,
    onValueChange: (String) -> Unit,
    backgroundColor: Color,
    modifier: Modifier = Modifier,
    initialScrollItem: String? = null
) {
    var rowWidth by remember { mutableStateOf(0.dp) }
    var rowHeight by remember { mutableIntStateOf(0) }
    val density = LocalDensity.current

    var showPopup by remember { mutableStateOf(false) }
    var isExpanded by remember { mutableStateOf(false) }

    val bringIntoViewRequester = remember { BringIntoViewRequester() }
    val animationTime = 300
    val spacerHeight = if (showPopup) (DROPBOX_ITEM_HEIGHT * VISIBLE_DROPBOX_COUNT) + 36.dp else 36.dp

    val toggleDropdown = {
        if (isExpanded) {
            isExpanded = false
        } else {
            showPopup = true
        }
    }

    val focusManager = LocalFocusManager.current

    LaunchedEffect(spacerHeight) {
        if (spacerHeight > 0.dp) {
            bringIntoViewRequester.bringIntoView()
        }
    }

    LaunchedEffect(showPopup) {
        if (showPopup) {
            delay(100)
            isExpanded = true
        }
    }

    LaunchedEffect(isExpanded) {
        if (!isExpanded && showPopup) {
            delay(animationTime.toLong())
            showPopup = false
        }
    }

    Column(
        modifier = modifier
            .width(IntrinsicSize.Max)
            .bringIntoViewRequester(bringIntoViewRequester)
            .navigationBarsPadding()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .roundedBackgroundWithBorder(
                    cornerRadius = 8.dp,
                    backgroundColor = backgroundColor,
                    borderColor = CertiTheme.colors.gray200,
                    borderWidth = 1.dp
                )
                .noRippleClickable {
                    focusManager.clearFocus()
                    toggleDropdown()
                }
                .onSizeChanged {
                    rowWidth = with(density) { it.width.toDp() }
                    rowHeight = it.height
                }
                .padding(vertical = screenHeightDp(8.dp), horizontal = screenWidthDp(12.dp)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = if (value.isEmpty()) placeholder else value,
                style = CertiTheme.typography.caption.semibold_12,
                color = if (value.isEmpty()) CertiTheme.colors.gray300 else CertiTheme.colors.black,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.weight(1f)
            )

            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_arrowdown_24),
                contentDescription = null,
                tint = CertiTheme.colors.gray400
            )
        }
        if (showPopup) {
            Popup(
                alignment = Alignment.TopStart,
                offset = IntOffset(x = 0, y = rowHeight),
                onDismissRequest = { isExpanded = false },
                properties = PopupProperties(focusable = true)
            ) {
                AnimatedVisibility(
                    visible = isExpanded,
                    enter = fadeIn(animationSpec = tween(animationTime)) +
                        expandVertically(
                            animationSpec = tween(animationTime),
                            expandFrom = Alignment.Top
                        ),
                    exit = fadeOut(animationSpec = tween(animationTime)) +
                        shrinkVertically(
                            animationSpec = tween(animationTime),
                            shrinkTowards = Alignment.Top
                        )
                ) {
                    val lazyListState = rememberLazyListState()
                    val targetItem = if (value.isNotEmpty()) value else initialScrollItem
                    val initialIndex = remember(items, targetItem) {
                        targetItem?.let { items.indexOf(it) } ?: -1
                    }

                    LaunchedEffect(initialIndex) {
                        if (initialIndex >= 0) {
                            lazyListState.scrollToItem(index = initialIndex)
                        }
                    }

                    LazyColumn(
                        state = lazyListState,
                        modifier = Modifier
                            .dropShadow(
                                shape = RoundedCornerShape(4.dp),
                                color = CertiTheme.colors.black.copy(alpha = 0.05f),
                                blur = 20.dp
                            )
                            .clip(RoundedCornerShape(4.dp))
                            .heightForScreenPercentage(DROPBOX_ITEM_HEIGHT * VISIBLE_DROPBOX_COUNT)
                            .background(CertiTheme.colors.white)
                            .width(rowWidth)
                    ) {
                        items(items) { item ->
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .heightForScreenPercentage(DROPBOX_ITEM_HEIGHT)
                                    .noRippleClickable {
                                        onValueChange(item)
                                        isExpanded = false
                                    },
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = item,
                                    style = CertiTheme.typography.caption.semibold_12,
                                    color = CertiTheme.colors.black,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                                HorizontalDivider(
                                    modifier = Modifier.align(Alignment.BottomCenter),
                                    color = CertiTheme.colors.gray100
                                )
                            }
                        }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.heightForScreenPercentage(spacerHeight))
    }
}

@Preview(showBackground = true)
@Composable
private fun MyPageDateInputFieldPreview() {
    var value by remember { mutableStateOf(DateData(2004, 4, 5)) }

    CERTITheme {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            DateInputField(
                label = "생년월일",
                value = value,
                onValueChange = { value = it },
                inputFieldBackgroundColor = CertiTheme.colors.white,
                modifier = Modifier.padding(horizontal = 20.dp)
            )
        }
    }
}
