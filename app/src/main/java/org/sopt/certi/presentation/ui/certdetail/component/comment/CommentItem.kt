package org.sopt.certi.presentation.ui.certdetail.component.comment

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.certi.R
import org.sopt.certi.core.util.heightForScreenPercentage
import org.sopt.certi.core.util.noRippleClickable
import org.sopt.certi.core.util.pressedClickable
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.core.util.widthForScreenPercentage
import org.sopt.certi.domain.model.comment.CommentItemData
import org.sopt.certi.domain.type.CertStateType
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun CommentItem(
    commentData: CommentItemData,
    myUserId: Long,
    likeOnClick: (like: Boolean) -> Unit = {},
    reportOnClick: () -> Unit = {},
    deleteOnClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    var acquireStateText by remember { mutableStateOf("") }
    var acquireStateTextColor by remember { mutableStateOf(Color.Black) }

    var isLikeStatus by remember { mutableStateOf(commentData.isLike) }
    var likeCountStatus by remember { mutableStateOf(commentData.likeCount) }

    when (commentData.state) {
        CertStateType.ANTICIPATED, -> {
            acquireStateText = stringResource(R.string.comment_state_acquired)
            acquireStateTextColor = CertiTheme.colors.purpleBlue
        }
        CertStateType.ACQUISITION -> {
            acquireStateText = stringResource(R.string.comment_state_pre)
            acquireStateTextColor = CertiTheme.colors.gray300
        }
        CertStateType.NORMAL -> {
            acquireStateText = "ERROR"
            acquireStateTextColor = CertiTheme.colors.error
        }
    }

    Column(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(screenHeightDp(8.dp))
    ) {
        Spacer(Modifier.heightForScreenPercentage(8.dp))

        // 사용자 닉네임, 취득 상태, 직무 정보, 삭제 버튼
        Row(
            horizontalArrangement = Arrangement.spacedBy(screenWidthDp(8.dp))
        ) {
            Text(
                text = commentData.nickName,
                style = CertiTheme.typography.caption.semibold_14,
                color = CertiTheme.colors.black
            )

            Text(
                text = acquireStateText,
                style = CertiTheme.typography.caption.semibold_12,
                color = acquireStateTextColor
            )

            Text(
                text = stringResource(R.string.comment_user_info, commentData.userMajor, commentData.userJob),
                style = CertiTheme.typography.caption.semibold_12,
                color = CertiTheme.colors.gray400
            )

            Spacer(Modifier.weight(1f))

            if (commentData.userId == myUserId) {
                CommentDeleteButton {
                    deleteOnClick()
                }
            }
        }

        // 댓글 내용
        Text(
            text = commentData.content,
            style = CertiTheme.typography.caption.regular_14,
            color = CertiTheme.colors.gray500
        )

        // 좋아요, 산고, 날짜
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(if (isLikeStatus) R.drawable.ic_like_filled else R.drawable.ic_like),
                tint = Color.Unspecified,
                modifier = Modifier
                    .widthForScreenPercentage(12.dp)
                    .heightForScreenPercentage(12.dp)
                    .noRippleClickable {
                        isLikeStatus = !isLikeStatus
                        likeCountStatus = if (isLikeStatus) likeCountStatus + 1 else likeCountStatus - 1
                        likeOnClick(isLikeStatus)
                    },
                contentDescription = null
            )

            Spacer(Modifier.widthForScreenPercentage(4.dp))

            Text(
                text = stringResource(R.string.comment_like_count, likeCountStatus),
                style = CertiTheme.typography.caption.semibold_12,
                color = CertiTheme.colors.gray400
            )

            Spacer(Modifier.widthForScreenPercentage(8.dp))

            VerticalDivider(thickness = 1.dp, color = CertiTheme.colors.gray300, modifier = Modifier.heightForScreenPercentage(12.dp))

            Spacer(Modifier.widthForScreenPercentage(8.dp))

            Text(
                text = stringResource(R.string.comment_report),
                style = CertiTheme.typography.caption.semibold_12,
                color = CertiTheme.colors.gray400,
                modifier = Modifier.noRippleClickable {
                    reportOnClick()
                }
            )

            Spacer(Modifier.widthForScreenPercentage(8.dp))

            Text(
                text = commentData.createdTime.split("T")[0].replace("-", "."),
                style = CertiTheme.typography.caption.semibold_12,
                color = CertiTheme.colors.gray400
            )
        }

        HorizontalDivider(thickness = 1.dp, color = CertiTheme.colors.gray100)
    }
}

@Composable
fun CommentDeleteButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    var isPressed by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .background(
                color = if (isPressed) CertiTheme.colors.gray0 else CertiTheme.colors.gray0,
                shape = RoundedCornerShape(12.dp)
            )
            .clip(RoundedCornerShape(16.dp))
            .padding(horizontal = screenWidthDp(8.dp), vertical = screenHeightDp(2.dp))
            .pressedClickable(
                changePressed = {
                    isPressed = it
                },
                onClick = onClick
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(R.string.comment_delete_button),
            style = CertiTheme.typography.caption.regular_12,
            color = CertiTheme.colors.black
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CommentItemPreview() {
    val sampleComment = CommentItemData(
        commentId = 1L,
        userId = 1L,
        nickName = "SOPT",
        content = "이 자격증 너무 좋은 것 같아요! 다들 꼭 따세요~이 자격증 너무 좋은 것 같아요! 다들 꼭 따세요~이 자격증 너무 좋은 것 같아요! 다들 꼭 따세요~",
        userMajor = "컴퓨터공학과",
        userJob = "개발자",
        state = CertStateType.ANTICIPATED,
        createdTime = "2026-02-05T03:25:01.699655",
        lastModifiedTime = "2024.07.21",
        isLike = true,
        likeCount = 15
    )

    CommentItem(commentData = sampleComment, myUserId = 1L)
}
