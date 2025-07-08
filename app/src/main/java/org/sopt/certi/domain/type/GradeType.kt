package org.sopt.certi.domain.type

enum class GradeType(
    val description: String
) {
    FRESHMAN(
        description = "1학년"
    ),
    SOPHOMORE(
        description = "2학년"
    ),
    JUNIOR(
        description = "3학년"
    ),
    SENIOR(
        description = "4학년 이상"
    ),
    GRADUATE(
        description = "졸업/졸업유예"
    )
}
