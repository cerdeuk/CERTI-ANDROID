package org.sopt.certi.domain.type

enum class CategoryType(
    val description: String
) {
    BUSINESS(
        description = "경영/사무"
    ),
    MARKETING(
        description = "마케팅/광고/홍보"
    ),
    TRADE(
        description = "무역/유통"
    ),
    IT(
        description = "IT/인터넷"
    ),
    MANUFACTURE(
        description = "생산/제조"
    ),
    SALES(
        description = "영업/고객상담"
    ),
    CONSTRUCTION(
        description = "건설"
    ),
    FINANCE(
        description = "금융"
    ),
    RND(
        description = "연구개발/설계"
    ),
    DESIGN(
        description = "디자인"
    ),
    MEDIA(
        description = "미디어"
    ),
    PROFESSIONAL(
        description = "전문/특수직"
    )
}