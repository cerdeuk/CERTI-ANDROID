package org.sopt.certi.data.mapper.todomain.user

import org.sopt.certi.data.remote.dto.response.GetMyPageResponseDto
import org.sopt.certi.domain.model.user.CertificationCount
import org.sopt.certi.domain.model.user.MyPageInfo

fun GetMyPageResponseDto.toDomain(): MyPageInfo = MyPageInfo(
    nickname = nickname,
    email = email,
    profileImageUrl = profileImageURL,
    jobs = jobResponse.jobList,
    certificationCount = CertificationCount(
        planned = upCount,
        acquired = acCount,
        favorite = fCount
    )
)