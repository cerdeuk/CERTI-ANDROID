package org.sopt.certi.data.mapper.todomain.image

import org.sopt.certi.data.remote.dto.response.PresignedResponseDto
import org.sopt.certi.domain.model.image.PresignedData

fun PresignedResponseDto.toDomain(): PresignedData = PresignedData(
    presignedUrl = preSignedURL,
    publicUrl = publicURL
)
