package org.sopt.certi.domain.model.comment

import org.sopt.certi.domain.type.CertAcquireStateType

data class CommentData(
    val content: List<CommentItemData>,
    val totalPages: Int, // Integer	댓글 총 페이지
    val totalElements: Int, // Integer	댓글 요소
    val isLast: Boolean // Boolean	마지막 페이지인지
)

data class CommentItemData(
    val commentId: Long, // 댓글 id
    val userId: Long, // 사용자 id
    val nickName: String, // 사용자 닉네임
    val content: String, // 댓글 내용
    val userMajor: String, // 사용자 전공
    val userJob: String, // 사용자 직무 정보(현재 123순위 기능 구현 안해서, 그냥 직무 정보중 첫번째로 가져옴)
    val state: CertAcquireStateType, // 취득 예정인지, 취득인지
    val createdTime: String, // 생성일자
    val lastModifiedTime: String, // 수정일자
    val isLike: Boolean, // 댓글을 조회하는 사용자가 해당 댓글에 좋아요를 눌렀는지
    val likeCount: Int // 좋아요 갯수
)
