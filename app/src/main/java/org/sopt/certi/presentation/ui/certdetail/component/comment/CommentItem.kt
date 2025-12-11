package org.sopt.certi.presentation.ui.certdetail.component.comment

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.certi.R
import org.sopt.certi.core.util.heightForScreenPercentage
import org.sopt.certi.core.util.screenHeightDp
import org.sopt.certi.core.util.screenWidthDp
import org.sopt.certi.core.util.widthForScreenPercentage
import org.sopt.certi.domain.model.comment.CommentData
import org.sopt.certi.domain.type.CertAcquireStateType
import org.sopt.certi.presentation.ui.certdetail.component.button.CommentDeleteButton
import org.sopt.certi.ui.theme.CertiTheme

@Composable
fun CommentItem(
    commentData: CommentData,
    myUserId: Long,
    modifier: Modifier = Modifier
) {
    var acquireStateText by remember { mutableStateOf("") }
    var acquireStateTextColor by remember { mutableStateOf(Color.Black) }

    when (commentData.state) {
        CertAcquireStateType.ACQUIRED -> {
            acquireStateText = stringResource(R.string.comment_state_acquired)
            acquireStateTextColor = CertiTheme.colors.purpleBlue
        }
        CertAcquireStateType.PRE -> {
            acquireStateText = stringResource(R.string.comment_state_pre)
            acquireStateTextColor = CertiTheme.colors.gray300
        }
    }

    Column(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(screenHeightDp(8.dp))
    ) {
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
                text = "(${commentData.userMajor}, ${commentData.userJob})",
                style = CertiTheme.typography.caption.semibold_12,
                color = CertiTheme.colors.gray400
            )

            Spacer(Modifier.weight(1f))

            if (commentData.userId == myUserId) {
                CommentDeleteButton {
                    // TODO delete comment
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
                painter = painterResource(if (commentData.isLike) R.drawable.ic_like else R.drawable.ic_like),
                tint = Color.Unspecified,
                modifier = Modifier
                    .widthForScreenPercentage(12.dp)
                    .heightForScreenPercentage(12.dp),
                contentDescription = null
            )

            Spacer(Modifier.widthForScreenPercentage(4.dp))

            Text(
                text = stringResource(R.string.comment_like_count, commentData.likeCount),
                style = CertiTheme.typography.caption.semibold_12,
                color = CertiTheme.colors.gray400
            )

            Spacer(Modifier.widthForScreenPercentage(8.dp))

            VerticalDivider(thickness = 1.dp, color = CertiTheme.colors.gray300, modifier = Modifier.heightForScreenPercentage(12.dp))

            Spacer(Modifier.widthForScreenPercentage(8.dp))

            Text(
                text = stringResource(R.string.comment_report),
                style = CertiTheme.typography.caption.semibold_12,
                color = CertiTheme.colors.gray400
            )

            Spacer(Modifier.widthForScreenPercentage(8.dp))

            Text(
                text = commentData.createdTime,
                style = CertiTheme.typography.caption.semibold_12,
                color = CertiTheme.colors.gray400
            )
        }

        HorizontalDivider(thickness = 1.dp, color = CertiTheme.colors.gray100)
    }
}

@Preview(showBackground = true)
@Composable
fun CommentItemPreview() {
    val sampleComment = CommentData(
        commentId = 1L,
        userId = 1L,
        nickName = "SOPT",
        content = "이 자격증 너무 좋은 것 같아요! 다들 꼭 따세요~이 자격증 너무 좋은 것 같아요! 다들 꼭 따세요~이 자격증 너무 좋은 것 같아요! 다들 꼭 따세요~",
        userMajor = "컴퓨터공학과",
        userJob = "개발자",
        state = CertAcquireStateType.ACQUIRED,
        createdTime = "2024.07.21",
        lastModifiedTime = "2024.07.21",
        isLike = true,
        totalPages = 1,
        totalElements = 1,
        isLast = true,
        likeCount = 15
    )

    CommentItem(commentData = sampleComment, myUserId = 1L)
}
