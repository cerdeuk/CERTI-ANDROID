package org.sopt.certi.di

import com.kakao.sdk.user.model.User
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.certi.domain.repository.AuthRepository
import org.sopt.certi.domain.repository.CertRepository
import org.sopt.certi.domain.repository.DummyRepository
import org.sopt.certi.domain.repository.HomeRepository
import org.sopt.certi.domain.repository.UserRepository
import org.sopt.certi.domain.usecase.DummyUseCase
import org.sopt.certi.domain.usecase.FavoriteUseCase
import org.sopt.certi.domain.usecase.PreCertUseCase
import org.sopt.certi.domain.usecase.SearchMajorUseCase
import org.sopt.certi.domain.usecase.SearchUnivUseCase
import org.sopt.certi.domain.usecase.SignInUseCase
import org.sopt.certi.domain.usecase.SignUpUseCase
import org.sopt.certi.domain.usecase.ToggleFavoriteUseCase
import org.sopt.certi.domain.usecase.UserInfoUseCase
import org.sopt.certi.domain.usecase.certification.GetCategoryCertListUseCase
import org.sopt.certi.domain.usecase.certification.GetRecommendCertListUseCase
import org.sopt.certi.domain.usecase.certification.SearchCertListUseCase
import org.sopt.certi.domain.usecase.user.GetInterestedJobListUseCase
import org.sopt.certi.domain.usecase.user.ModifyInterestedJobListUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    @Singleton
    fun provideDummyUseCase(
        dummyRepository: DummyRepository
    ): DummyUseCase = DummyUseCase(dummyRepository)

    @Provides
    @Singleton
    fun provideSignInUseCase(
        authRepository: AuthRepository
    ): SignInUseCase = SignInUseCase(authRepository)

    @Provides
    @Singleton
    fun provideSignUpUseCase(
        authRepository: AuthRepository
    ): SignUpUseCase = SignUpUseCase(authRepository)

    @Provides
    @Singleton
    fun provideSearchUnivUseCase(
        authRepository: AuthRepository
    ): SearchUnivUseCase = SearchUnivUseCase(authRepository)

    @Provides
    @Singleton
    fun provideSearchMajorUseCase(
        authRepository: AuthRepository
    ): SearchMajorUseCase = SearchMajorUseCase(authRepository)

    @Provides
    @Singleton
    fun provideGetInterestedJobListUseCase(
        userRepository: UserRepository
    ): GetInterestedJobListUseCase = GetInterestedJobListUseCase(userRepository)

    @Provides
    @Singleton
    fun provideGetRecommendCertListUseCase(
        certRepository: CertRepository
    ): GetRecommendCertListUseCase = GetRecommendCertListUseCase(certRepository)

    @Provides
    @Singleton
    fun provideModifyRecommendCertListUseCase(
        userRepository: UserRepository
    ): ModifyInterestedJobListUseCase = ModifyInterestedJobListUseCase(userRepository)

    @Provides
    @Singleton
    fun provideSearchCerListUseCase(
        certRepository: CertRepository
    ): SearchCertListUseCase = SearchCertListUseCase(certRepository)

    @Provides
    @Singleton
    fun provideGetCategoryCertListUseCase(
        certRepository: CertRepository
    ): GetCategoryCertListUseCase = GetCategoryCertListUseCase(certRepository)

    @Provides
    fun provideGetUserInfoUseCase(
        homeRepository: HomeRepository
    ): UserInfoUseCase = UserInfoUseCase(homeRepository)

    @Provides
    fun provideGetPreCertListUseCase(
        homeRepository: HomeRepository
    ): PreCertUseCase = PreCertUseCase(homeRepository)

    @Provides
    fun provideGetFavoriteListUseCase(
        homeRepository: HomeRepository
    ): FavoriteUseCase = FavoriteUseCase(homeRepository)

    @Provides
    fun provideToggleFavoriteUseCase(
        homeRepository: HomeRepository
    ): ToggleFavoriteUseCase = ToggleFavoriteUseCase(homeRepository)
}
