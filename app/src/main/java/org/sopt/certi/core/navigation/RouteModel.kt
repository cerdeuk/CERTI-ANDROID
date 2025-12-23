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
    data object NickName : OnBoardingRoute

    @Serializable
    data object OnBoardingInfo : OnBoardingRoute
}

sealed interface MainTabRoute : Route {
    @Serializable
    data object Home : MainTabRoute

    @Serializable
    data object CertList : MainTabRoute

    @Serializable
    data object Resume : MainTabRoute

    @Serializable
    data object MyPage : MainTabRoute
}

sealed interface HomeRoute : Route {
    @Serializable
    data object CertPlanned : HomeRoute

    @Serializable
    data object CertRecommended : HomeRoute

    @Serializable
    data object CertSaved : HomeRoute
}

sealed interface ResumeRoute : Route {
    @Serializable
    data object WorkExperience : ResumeRoute

    @Serializable
    data object AddWorkExperience : ResumeRoute

    @Serializable
    data object Activities : ResumeRoute

    @Serializable
    data object AddActivities : ResumeRoute
}

sealed interface CertRecommendRoute : Route {
    @Serializable
    data object CertDetail : CertRecommendRoute {
        const val DETAIL_ROUTE = "certDetail/{certId}"
        fun certDetailRoute(certId: Long) = "certDetail/$certId"
    }
}

sealed interface CertListRoute : Route {
    @Serializable
    data class TrackCategoryCertList(
        val mode: String
    ) : CertListRoute

    @Serializable
    data object Search : CertListRoute
}

sealed interface MyPageRoute : Route {
    @Serializable
    data object MyCertification : MyPageRoute

    @Serializable
    data object EditMyCertification : MyPageRoute
}
