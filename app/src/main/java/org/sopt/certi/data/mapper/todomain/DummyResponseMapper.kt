package org.sopt.certi.data.mapper.todomain

import org.sopt.certi.data.remote.dto.DummyResponseDto
import org.sopt.certi.domain.model.DummyData

fun DummyResponseDto.toDomain(): DummyData {
    return DummyData(
        description = this.dummy + "입니다 "
    )
}
