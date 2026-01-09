package org.sopt.certi.presentation.ui.mycertification

import org.sopt.certi.domain.model.certification.CertificationData

val dummyPlannedCertifications = listOf(
    CertificationData(
        certificationId = 1,
        certificationName = "정보처리기사",
        certificationType = "국가기술자격",
        description = "소프트웨어 개발 관련 자격증으로, 계획수립, 분석, 설계, 구...",
        isAcquired = false,
        city = "고양시",
        state = "고양시",
        testTime = "09:00",
        testDateInformation = "2025.12.11",
        isFavorite = true
    ),
    CertificationData(
        certificationId = 2,
        certificationName = "정보처리기사",
        certificationType = "국가기술자격",
        description = "소프트웨어 개발 관련 자격증으로, 계획수립, 분석, 설계, 구...",
        isAcquired = false,
        city = "고양시",
        state = "고양시",
        testTime = "09:00",
        testDateInformation = "2025.12.11",
        isFavorite = true
    ),
    CertificationData(
        certificationId = 3,
        certificationName = "정보처리기사",
        certificationType = "국가기술자격",
        description = "소프트웨어 개발 관련 자격증으로, 계획수립, 분석, 설계, 구...",
        isAcquired = false,
        city = "고양시",
        state = "고양시",
        testTime = "09:00",
        testDateInformation = "2025.12.11",
        isFavorite = true
    )
)

val dummyAcquiredCertification = listOf(
    CertificationData(
        certificationId = 4,
        certificationName = "정보처리기사",
        certificationType = "국가기술자격",
        description = "소프트웨어 개발 관련 자격증으로, 계획수립, 분석, 설계, 구...",
        isAcquired = true,
        city = "고양시",
        state = "고양시",
        testTime = "09:00",
        acquisitionDate = "2025.12.11",
        grade = "IM3",
        isFavorite = true
    ),
    CertificationData(
        certificationId = 5,
        certificationName = "정보처리기사",
        certificationType = "국가기술자격",
        description = "소프트웨어 개발 관련 자격증으로, 계획수립, 분석, 설계, 구...",
        isAcquired = true,
        city = "고양시",
        state = "고양시",
        testTime = "09:00",
        acquisitionDate = "2025.12.11",
        grade = "IM3",
        isFavorite = true
    )
)
