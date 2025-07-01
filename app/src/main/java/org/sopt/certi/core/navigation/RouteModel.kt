package org.sopt.certi.core.navigation

import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    data object Splash : Route

    @Serializable
    data object Login : Route
}

sealed interface OnBoardingRoute : Route {
    @Serializable
    data object Univ : OnBoardingRoute

    @Serializable
    data object Grade : OnBoardingRoute

    @Serializable
    data object Track : OnBoardingRoute

    @Serializable
    data object Major : OnBoardingRoute

    @Serializable
    data object JobCategory : OnBoardingRoute

    @Serializable
    data object OnBoardingInfo : OnBoardingRoute
}

sealed interface MainTabRoute : Route {
    @Serializable
    data object Home : MainTabRoute

    @Serializable
    data object CertList : MainTabRoute

    @Serializable
    data object CertRecommend : MainTabRoute

    @Serializable
    data object Resume : MainTabRoute
}

sealed interface HomeRoute : Route {
    @Serializable
    data object CertPlanned : HomeRoute

    @Serializable
    data object CertSaved : HomeRoute
}

sealed interface ResumeRoute : Route {
    @Serializable
    data object MyCert : ResumeRoute

    @Serializable
    data object WorkExperience : ResumeRoute

    @Serializable
    data object Activities : ResumeRoute
}

sealed interface CertRecommendRoute : Route {
    @Serializable
    data object CertDetail : CertRecommendRoute
}

sealed interface CertListRoute : Route {
    @Serializable
    data object Search : CertListRoute
}