package org.sopt.certi.domain.type

enum class CertStateType(val stateName: String) {
    NORMAL("아무것도 아님"), // 아무것도 아님
    ANTICIPATED("취득 예정"), // 취득 예정
    ACQUISITION("취득 완료"); // 취득 완료

    companion object {
        fun fromStateName(stateName: String): CertStateType {
            return entries.find { it.stateName == stateName }
                ?: NORMAL
        }
    }
}
