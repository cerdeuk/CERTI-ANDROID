package org.sopt.certi.data.remote.service

import org.sopt.certi.data.remote.dto.base.ApiResponse
import org.sopt.certi.data.remote.dto.base.NullableApiResponse
import org.sopt.certi.data.remote.dto.request.comment.RegisterCommentRequestDto
import org.sopt.certi.data.remote.dto.response.comment.GetCommentListResponseDto
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface CommentService {
    @GET("api/v1/comments")
    suspend fun getCommentList(
        @Query("certificationId") certificationId: Long,
        @Query("page") page: Int,
        @Query("size") size: Int,
        @Query("commentSortType") commentSortType: String? = null
    ): ApiResponse<GetCommentListResponseDto>

    @POST("api/v1/comments")
    suspend fun registerComment(@Body registerCommentRequest: RegisterCommentRequestDto): NullableApiResponse<Unit>

    @POST("api/v1/comments/{commentId}/like")
    suspend fun likeComment(@Path("commentId") commentId: Long): NullableApiResponse<Unit>

    @DELETE("api/v1/comments/{commentId}")
    suspend fun deleteComment(@Path("commentId") commentId: Long): NullableApiResponse<Unit>
}
