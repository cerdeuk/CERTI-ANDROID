package org.sopt.certi.domain.type

enum class TrackType(
    val description: String
) {
    HUMANITIES(
        description = "인문계열"
    ),
    SOCIAL(
        description = "사회계열"
    ),
    EDUCATION(
        description = "교육계열"
    ),
    NATURAL(
        description = "자연계열"
    ),
    ENGINEERING(
        description = "공학계열"
    ),
    MEDICAL(
        description = "의약계열"
    ),
    ARTS(
        description = "예체능계열"
    )
}
