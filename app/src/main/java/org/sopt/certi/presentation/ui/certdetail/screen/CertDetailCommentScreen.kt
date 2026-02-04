package org.sopt.certi.presentation.ui.certdetail.screen

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.isImeVisible
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import kotlinx.coroutines.flow.flowOf
import org.sopt.certi.R
import org.sopt.certi.core.util.heightForScreenPercentage
import org.sopt.certi.core.util.noRippleClickable
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.core.util.widthForScreenPercentage
import org.sopt.certi.domain.model.comment.CommentItemData
import org.sopt.certi.domain.type.CertStateType
import org.sopt.certi.presentation.type.CommentSortType
import org.sopt.certi.presentation.ui.certdetail.CertDetailViewModel
import org.sopt.certi.presentation.ui.certdetail.component.chip.CommentArrayButton
import org.sopt.certi.presentation.ui.certdetail.component.comment.CommentEmptyView
import org.sopt.certi.presentation.ui.certdetail.component.comment.CommentItem
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun CertDetailCommentRoute(
    certificationId: Long,
    viewModel: CertDetailViewModel = hiltViewModel()
) {
    var commentSortType by remember { mutableStateOf(CommentSortType.Famous) }
    val commentList = viewModel.commentPagingData.collectAsLazyPagingItems()
    val totalCommentCount by viewModel.totalCommentCount.collectAsStateWithLifecycle()

    LaunchedEffect(commentSortType) {
        viewModel.getCommentList(certificationId, commentSortType)
    }

    CertDetailCommentScreen(
        commentData = commentList,
        totalCommentCount = totalCommentCount,
        myUserId = 0,
        changeSortType = { changedSortType ->
            commentSortType = changedSortType
        },
        writeComment = { content ->

        },
        likeOnClick = { like, commentId ->

        },
        reportOnClick = { commentId ->

        },
        deleteOnClick = { commentId ->

        }
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CertDetailCommentScreen(
    commentData: LazyPagingItems<CommentItemData>,
    totalCommentCount: Int,
    myUserId: Long,
    changeSortType: (CommentSortType) -> Unit = {},
    writeComment: (content: String) -> Unit = {},
    likeOnClick: (like: Boolean, commentId: Long) -> Unit = { _, _ -> },
    reportOnClick: (commentId: Long) -> Unit = {},
    deleteOnClick: (commentId: Long) -> Unit = {}
) {
    var commentSortType by remember { mutableStateOf(CommentSortType.Famous) }

    var commentText by remember { mutableStateOf("") }

    val imeVisible = if (LocalInspectionMode.current) false else WindowInsets.isImeVisible
    val navigationBarHeight = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()
    val keyboardHeight = WindowInsets.ime.asPaddingValues().calculateBottomPadding()

    val textFieldOffset by animateDpAsState(
        targetValue = if (imeVisible) {
            keyboardHeight - navigationBarHeight
        } else {
            0.dp
        },
        label = "BottomPadding"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = screenHeightDp(36.dp))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(horizontal = screenWidthDp(20.dp))
        ) {
            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                CommentArrayButton(
                    commentSortType = CommentSortType.Famous,
                    isSelected = commentSortType == CommentSortType.Famous,
                    selectOnClick = {
                        commentSortType = CommentSortType.Famous
                        changeSortType(commentSortType)
                    }
                )

                Spacer(Modifier.widthForScreenPercentage(8.dp))

                CommentArrayButton(
                    commentSortType = CommentSortType.Recent,
                    isSelected = commentSortType == CommentSortType.Recent,
                    selectOnClick = {
                        commentSortType = CommentSortType.Recent
                        changeSortType(commentSortType)
                    }
                )

                Spacer(Modifier.weight(1f))

                Text(
                    text = stringResource(R.string.comment_count, totalCommentCount),
                    style = CertiTheme.typography.caption.regular_14,
                    color = CertiTheme.colors.gray400
                )
            }

            if(commentData.itemCount == 0) {
                CommentEmptyView()
            } else {
                LazyColumn(
                    contentPadding = PaddingValues(top = screenHeightDp(12.dp)),
                    verticalArrangement = Arrangement.spacedBy(screenHeightDp(12.dp))
                ) {
                    items(
                        count = commentData.itemCount
                    ) { index ->
                        commentData[index]?.let { comment ->
                            CommentItem(
                                commentData = comment,
                                myUserId = myUserId,
                                likeOnClick = { like ->
                                    likeOnClick(like, comment.commentId)
                                },
                                reportOnClick = {
                                    reportOnClick(comment.commentId)
                                },
                                deleteOnClick = {
                                    deleteOnClick(comment.commentId)
                                }
                            )
                        }
                    }
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = -textFieldOffset)
                .drawBehind {
                    // 위쪽 그림자만 그리기
                    val shadowHeight = 4.dp.toPx()
                    val shadowColor = Color.Black.copy(alpha = 0.025f)

                    drawRect(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                shadowColor,
                                Color.Transparent
                            ),
                            startY = 0f,
                            endY = shadowHeight
                        ),
                        topLeft = Offset(0f, -shadowHeight),
                        size = Size(size.width, shadowHeight)
                    )
                }
                .background(CertiTheme.colors.white)
                .padding(vertical = screenHeightDp(20.dp), horizontal = screenWidthDp(20.dp))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightForScreenPercentage(40.dp)
                    .background(color = CertiTheme.colors.gray100, shape = RoundedCornerShape(18.dp))
                    .padding(end = screenWidthDp(12.dp)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                BasicTextField(
                    value = commentText,
                    onValueChange = { commentText = it },
                    singleLine = true,
                    maxLines = 1,
                    textStyle = CertiTheme.typography.caption.regular_14.copy(
                        color = CertiTheme.colors.black
                    ),
                    cursorBrush = SolidColor(CertiTheme.colors.black),
                    decorationBox = { innerTextField ->
                        if (commentText.isEmpty()) {
                            Text(
                                text = stringResource(R.string.comment_hint),
                                style = CertiTheme.typography.caption.semibold_14,
                                color = CertiTheme.colors.gray300
                            )
                        }

                        innerTextField()
                    },
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = screenWidthDp(10.dp))
                )

                Spacer(Modifier.widthForScreenPercentage(12.dp))

                Icon(
                    painter = painterResource(R.drawable.ic_send),
                    tint = if (commentText.isEmpty()) Color.Unspecified else CertiTheme.colors.purpleBlue,
                    modifier = Modifier
                        .widthForScreenPercentage(24.dp)
                        .heightForScreenPercentage(24.dp)
                        .noRippleClickable {
                            writeComment(commentText)
                        },
                    contentDescription = null
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewCertDetailCommentScreen() {
    val dummyItem = CommentItemData(
        commentId = 1L,
        userId = 1L,
        nickName = "홍길동",
        content = "이 자격증 정말 추천합니다! 취업에 많은 도움이 되었어요.",
        userMajor = "컴퓨터공학",
        userJob = "백엔드 개발자",
        state = CertStateType.ACQUISITION,
        createdTime = "2024-01-15",
        lastModifiedTime = "2024-01-15",
        isLike = false,
        likeCount = 5
    )
    val dummyPagingData = PagingData.from(listOf(dummyItem))
    val dummyFlow = flowOf(dummyPagingData)
    val dummyList = dummyFlow.collectAsLazyPagingItems()

    CertDetailCommentScreen(commentData = dummyList, totalCommentCount = 1, myUserId = 0)
}
