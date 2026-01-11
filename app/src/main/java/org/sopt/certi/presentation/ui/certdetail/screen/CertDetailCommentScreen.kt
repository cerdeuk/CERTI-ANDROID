package org.sopt.certi.presentation.ui.certdetail.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.certi.R
import org.sopt.certi.core.util.heightForScreenPercentage
import org.sopt.certi.core.util.noRippleClickable
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.core.util.widthForScreenPercentage
import org.sopt.certi.domain.model.comment.CommentData
import org.sopt.certi.domain.model.comment.CommentItemData
import org.sopt.certi.domain.type.CertAcquireStateType
import org.sopt.certi.presentation.ui.certdetail.component.chip.CommentArrayButton
import org.sopt.certi.presentation.ui.certdetail.component.chip.CommentArrayButtonType
import org.sopt.certi.presentation.ui.certdetail.component.comment.CommentItem
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun CertDetailCommentRoute() {
    val dummyCommentData = CommentData(
        content = listOf(
            CommentItemData(
                commentId = 57,
                userId = 1,
                nickName = "이성민",
                content = "댓글입니다.",
                userMajor = "전산학/컴퓨터공학",
                userJob = "IT/인터넷",
                state = CertAcquireStateType.ACQUIRED,
                likeCount = 3,
                createdTime = "2025-11-15T23:00:38.042089",
                lastModifiedTime = "2025-11-15T23:00:38.042089",
                isLike = false
            )
        ),
        totalPages = 1,
        totalElements = 2,
        isLast = false
    )

    CertDetailCommentScreen(commentData = dummyCommentData, myUserId = 0)
}

@Composable
fun CertDetailCommentScreen(
    commentData: CommentData,
    myUserId: Long,
    writeComment: (content: String) -> Unit = {},
    likeOnClick: (like: Boolean, commentId: Long) -> Unit = { _, _ -> },
    reportOnClick: (commentId: Long) -> Unit = {},
    deleteOnClick: (commentId: Long) -> Unit = {}
) {
    var commentSortType by remember { mutableStateOf(CommentArrayButtonType.Famous) }

    var commentText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = screenHeightDp(36.dp))
            .padding(horizontal = screenWidthDp(20.dp))
    ) {
        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CommentArrayButton(
                commentArrayButtonType = CommentArrayButtonType.Famous,
                isSelected = commentSortType == CommentArrayButtonType.Famous,
                selectOnClick = {
                    commentSortType = CommentArrayButtonType.Famous
                }
            )

            Spacer(Modifier.widthForScreenPercentage(8.dp))

            CommentArrayButton(
                commentArrayButtonType = CommentArrayButtonType.Recent,
                isSelected = commentSortType == CommentArrayButtonType.Recent,
                selectOnClick = {
                    commentSortType = CommentArrayButtonType.Recent
                }
            )

            Spacer(Modifier.weight(1f))

            Text(
                text = stringResource(R.string.comment_count, commentData.totalElements),
                style = CertiTheme.typography.caption.regular_14,
                color = CertiTheme.colors.gray400
            )
        }

        LazyColumn(
            contentPadding = PaddingValues(top = screenHeightDp(12.dp)),
            verticalArrangement = Arrangement.spacedBy(screenHeightDp(12.dp))
        ) {
            items(commentData.content.size) { index ->
                CommentItem(
                    commentData = commentData.content[index],
                    myUserId = myUserId,
                    likeOnClick = { like ->
                        likeOnClick(like, commentData.content[index].commentId)
                    },
                    reportOnClick = {
                        reportOnClick(commentData.content[index].commentId)
                    },
                    deleteOnClick = {
                        deleteOnClick(commentData.content[index].commentId)
                    }
                )
            }
        }

        Spacer(Modifier.weight(1f))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightForScreenPercentage(83.dp)
                .padding(vertical = screenHeightDp(20.dp))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .heightForScreenPercentage(40.dp)
                    .background(color = CertiTheme.colors.gray100, shape = RoundedCornerShape(18.dp))
                    .padding(end = screenWidthDp(12.dp)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    value = commentText,
                    onValueChange = { commentText = it },
                    placeholder = {
                        Text(
                            text = stringResource(R.string.comment_hint),
                            style = CertiTheme.typography.caption.semibold_14,
                            color = CertiTheme.colors.gray300
                        )
                    },
                    singleLine = true,
                    maxLines = 1,
                    textStyle = CertiTheme.typography.caption.semibold_14.copy(color = CertiTheme.colors.black),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent,
                        errorContainerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    modifier = Modifier.weight(1f)
                )

                Spacer(Modifier.widthForScreenPercentage(12.dp))

                Icon(
                    painter = painterResource(R.drawable.ic_send),
                    tint = if (commentText.isEmpty()) Color.Unspecified else CertiTheme.colors.purpleBlue,
                    modifier = Modifier
                        .widthForScreenPercentage(24.dp)
                        .heightForScreenPercentage(24.dp)
                        .noRippleClickable{
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
    val dummyCommentData = CommentData(
        content = listOf(
            CommentItemData(
                commentId = 57,
                userId = 1,
                nickName = "이성민",
                content = "댓글입니다.",
                userMajor = "전산학/컴퓨터공학",
                userJob = "IT/인터넷",
                state = CertAcquireStateType.ACQUIRED,
                likeCount = 3,
                createdTime = "2025-11-15T23:00:38.042089",
                lastModifiedTime = "2025-11-15T23:00:38.042089",
                isLike = false
            ),
            CommentItemData(
                commentId = 58,
                userId = 1,
                nickName = "이성민2",
                content = "댓글입니다.2",
                userMajor = "전산학/컴퓨터공학",
                userJob = "IT/인터넷",
                state = CertAcquireStateType.ACQUIRED,
                likeCount = 3,
                createdTime = "2025-11-15T23:00:38.042089",
                lastModifiedTime = "2025-11-15T23:00:38.042089",
                isLike = false
            ),
            CommentItemData(
                commentId = 59,
                userId = 1,
                nickName = "이성민3",
                content = "댓글입니다.3",
                userMajor = "전산학/컴퓨터공학",
                userJob = "IT/인터넷",
                state = CertAcquireStateType.ACQUIRED,
                likeCount = 3,
                createdTime = "2025-11-15T23:00:38.042089",
                lastModifiedTime = "2025-11-15T23:00:38.042089",
                isLike = false
            ),
            CommentItemData(
                commentId = 60,
                userId = 1,
                nickName = "이성민4",
                content = "댓글입니다.4",
                userMajor = "전산학/컴퓨터공학",
                userJob = "IT/인터넷",
                state = CertAcquireStateType.ACQUIRED,
                likeCount = 3,
                createdTime = "2025-11-15T23:00:38.042089",
                lastModifiedTime = "2025-11-15T23:00:38.042089",
                isLike = false
            )
        ),
        totalPages = 1,
        totalElements = 4,
        isLast = false
    )

    CertDetailCommentScreen(commentData = dummyCommentData, myUserId = 0)
}
