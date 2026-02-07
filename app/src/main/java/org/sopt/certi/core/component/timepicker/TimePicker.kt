package org.sopt.certi.core.component.timepicker

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import org.sopt.certi.R
import org.sopt.certi.core.util.heightForScreenPercentage
import org.sopt.certi.core.util.widthForScreenPercentage
import org.sopt.certi.ui.theme.CertiTheme

enum class TimePeriodType() {
    AM, PM, NONE
}

@Composable
fun CustomTimePicker(
    modifier: Modifier = Modifier,
    initialHour: Int = 0,
    initialMinute: Int = 0,
    onTimeSelected: (hour: Int, minute: Int) -> Unit = { _, _ -> }
) {
    fun convertHour(h: Int) = if (h == 0 || h == -1) 12 else if (h > 12) h - 12 else h
    fun getPeriod(h: Int) = if (h < 12) TimePeriodType.AM else TimePeriodType.PM

    var selectedPeriod by remember { mutableStateOf(getPeriod(initialHour)) }
    var selectedHour by remember { mutableIntStateOf(convertHour(initialHour)) }
    var selectedMinute by remember { mutableIntStateOf(if (initialMinute == -1) 0 else initialMinute) }

    LaunchedEffect(initialHour, initialMinute) {
        selectedPeriod = getPeriod(initialHour)
        selectedHour = convertHour(initialHour)
        selectedMinute = if (initialMinute == -1) 0 else initialMinute
    }

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // 오전/오후 Picker
        TimePeriodPickerColumn(
            items = listOf(TimePeriodType.AM, TimePeriodType.PM),
            selectedItem = selectedPeriod,
            onItemSelected = {
                selectedPeriod = it
                onTimeSelected(
                    if (selectedPeriod == TimePeriodType.AM) {
                        if (selectedHour == 12) 0 else selectedHour
                    } else {
                        if (selectedHour == 12) 12 else selectedHour + 12
                    },
                    selectedMinute
                )
            },
            modifier = Modifier.widthForScreenPercentage(45.dp)
        )

        Spacer(modifier = Modifier.widthForScreenPercentage(47.dp))

        // 시간 Picker
        TimePickerColumn(
            items = (listOf(12) + (1..11)).map { it.toString().padStart(2, '0') },
            selectedItem = selectedHour.toString().padStart(2, '0'),
            onItemSelected = {
                selectedHour = it.toInt()
                onTimeSelected(
                    if (selectedPeriod == TimePeriodType.AM) {
                        if (selectedHour == 12) 0 else selectedHour
                    } else {
                        if (selectedHour == 12) 12 else selectedHour + 12
                    },
                    selectedMinute
                )
            },
            modifier = Modifier.widthForScreenPercentage(35.dp)
        )

        Text(
            text = stringResource(R.string.test_info_bottomsheet_time_picker_colon),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(horizontal = 22.dp)
                .widthForScreenPercentage(4.dp)
        )

        // 분 Picker
        TimePickerColumn(
            items = (0..59).map { it.toString().padStart(2, '0') },
            selectedItem = selectedMinute.toString().padStart(2, '0'),
            onItemSelected = {
                selectedMinute = it.toInt()
                onTimeSelected(
                    if (selectedPeriod == TimePeriodType.AM) {
                        if (selectedHour == 12) 0 else selectedHour
                    } else {
                        if (selectedHour == 12) 12 else selectedHour + 12
                    },
                    selectedMinute
                )
            },
            modifier = Modifier.widthForScreenPercentage(38.dp)
        )
    }
}

@Composable
fun TimePickerColumn(
    items: List<String>,
    selectedItem: String,
    onItemSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val listState = rememberLazyListState(
        initialFirstVisibleItemIndex = items.indexOf(selectedItem).coerceAtLeast(0)
    )
    val flingBehavior = rememberSnapFlingBehavior(lazyListState = listState)
    val coroutineScope = rememberCoroutineScope()

    // 현재 중앙에 있는 아이템 인덱스를 실시간으로 추적
    var currentCenterIndex by remember { mutableIntStateOf(listState.firstVisibleItemIndex) }

    // 스크롤 중에도 중앙 아이템 인덱스 업데이트
    LaunchedEffect(listState) {
        snapshotFlow { listState.firstVisibleItemIndex }
            .collect { index ->
                currentCenterIndex = index
            }
    }

    var programmaticScroll by remember { mutableStateOf(false) }

    // selectedItem이 변경되면 해당 위치로 스크롤
    LaunchedEffect(selectedItem) {
        val index = items.indexOf(selectedItem)
        if (index >= 0) {
            programmaticScroll = true
            coroutineScope.launch {
                listState.scrollToItem(index)
            }.invokeOnCompletion {
                programmaticScroll = false
            }
        }
    }

    // 스크롤이 멈췄을 때 선택 확정
    LaunchedEffect(listState, programmaticScroll) {
        snapshotFlow { listState.isScrollInProgress }
            .filter { !it && !programmaticScroll } // 스크롤이 멈췄을 때
            .collect {
                val centerIndex = listState.firstVisibleItemIndex
                if (centerIndex in items.indices) {
                    onItemSelected(items[centerIndex])
                }
            }
    }

    Box(
        modifier = modifier.heightForScreenPercentage(120.dp),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightForScreenPercentage(40.dp)
        ) {
            // 상단 파란색 라인
            HorizontalDivider(
                thickness = 2.dp,
                color = CertiTheme.colors.purpleBlue,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopCenter)
            )

            // 하단 파란색 라인
            HorizontalDivider(
                thickness = 2.dp,
                color = CertiTheme.colors.purpleBlue,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
            )
        }

        LazyColumn(
            state = listState,
            flingBehavior = flingBehavior,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            // 위 패딩
            items(1) {
                Spacer(modifier = Modifier.heightForScreenPercentage(40.dp))
            }

            items(items.size) { index ->
                val item = items[index]
                val isSelected = index == currentCenterIndex

                Text(
                    text = item,
                    style = CertiTheme.typography.caption.semibold_14,
                    color = if (isSelected) CertiTheme.colors.black else CertiTheme.colors.gray300,
                    modifier = Modifier
                        .heightForScreenPercentage(40.dp)
                        .wrapContentHeight()
                )
            }

            // 아래 패딩
            items(1) {
                Spacer(modifier = Modifier.heightForScreenPercentage(40.dp))
            }
        }
    }
}

@Composable
fun TimePeriodPickerColumn(
    items: List<TimePeriodType>,
    selectedItem: TimePeriodType,
    onItemSelected: (TimePeriodType) -> Unit,
    modifier: Modifier = Modifier
) {
    val listState = rememberLazyListState(
        initialFirstVisibleItemIndex = items.indexOf(selectedItem).coerceAtLeast(0)
    )
    val flingBehavior = rememberSnapFlingBehavior(lazyListState = listState)
    val coroutineScope = rememberCoroutineScope()

    // 현재 중앙에 있는 아이템 인덱스를 실시간으로 추적
    var currentCenterIndex by remember { mutableIntStateOf(listState.firstVisibleItemIndex) }

    // 스크롤 중에도 중앙 아이템 인덱스 업데이트
    LaunchedEffect(listState) {
        snapshotFlow { listState.firstVisibleItemIndex }
            .collect { index ->
                currentCenterIndex = index
            }
    }

    var programmaticScroll by remember { mutableStateOf(false) }

    // selectedItem이 변경되면 해당 위치로 스크롤
    LaunchedEffect(selectedItem) {
        val index = items.indexOf(selectedItem)
        if (index >= 0) {
            programmaticScroll = true
            coroutineScope.launch {
                listState.scrollToItem(index)
            }.invokeOnCompletion {
                programmaticScroll = false
            }
        }
    }

    // 스크롤이 멈췄을 때 선택 확정
    LaunchedEffect(listState, programmaticScroll) {
        snapshotFlow { listState.isScrollInProgress }
            .filter { !it && !programmaticScroll } // 스크롤이 멈췄을 때
            .collect {
                val centerIndex = listState.firstVisibleItemIndex
                if (centerIndex in items.indices) {
                    onItemSelected(items[centerIndex])
                }
            }
    }

    Box(
        modifier = modifier.heightForScreenPercentage(120.dp),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightForScreenPercentage(40.dp)
        ) {
            // 상단 파란색 라인
            HorizontalDivider(
                thickness = 2.dp,
                color = CertiTheme.colors.purpleBlue,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopCenter)
            )
            // 하단 파란색 라인
            HorizontalDivider(
                thickness = 2.dp,
                color = CertiTheme.colors.purpleBlue,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
            )
        }

        LazyColumn(
            state = listState,
            flingBehavior = flingBehavior,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            // 위 패딩
            items(1) {
                Spacer(modifier = Modifier.heightForScreenPercentage(40.dp))
            }

            items(items.size) { index ->
                val item = items[index]
                val isSelected = index == currentCenterIndex
                val itemTitle = when (item) {
                    TimePeriodType.AM -> stringResource(R.string.test_info_bottomsheet_time_morning)
                    TimePeriodType.PM -> stringResource(R.string.test_info_bottomsheet_time_afternoon)
                    TimePeriodType.NONE -> ""
                }

                Text(
                    text = itemTitle,
                    style = CertiTheme.typography.caption.semibold_14,
                    color = if (isSelected) CertiTheme.colors.black else CertiTheme.colors.gray300,
                    modifier = Modifier
                        .heightForScreenPercentage(40.dp)
                        .wrapContentHeight()
                )
            }

            // 아래 패딩
            items(1) {
                Spacer(modifier = Modifier.heightForScreenPercentage(40.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TimePickerPreview() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(CertiTheme.colors.white)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        CustomTimePicker(
            initialHour = 12,
            initialMinute = 0,
            onTimeSelected = { hour, minute -> }
        )
    }
}
