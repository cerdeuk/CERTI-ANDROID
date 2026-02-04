package org.sopt.certi.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.certi.domain.repository.ActivityRepository
import org.sopt.certi.domain.repository.AcquisitionRepository
import org.sopt.certi.domain.repository.AuthRepository
import org.sopt.certi.domain.repository.CareerRepository
import org.sopt.certi.domain.repository.CertRepository
import org.sopt.certi.domain.repository.CommentRepository
import org.sopt.certi.domain.repository.DummyRepository
import org.sopt.certi.domain.repository.HomeRepository
import org.sopt.certi.domain.repository.PreCertEditRepository
import org.sopt.certi.domain.repository.PreCertRepository
import org.sopt.certi.domain.repository.UserRepository
import org.sopt.certi.domain.usecase.activity.AddActivityUseCase
import org.sopt.certi.domain.usecase.career.AddCareerUseCase
import org.sopt.certi.domain.usecase.acquisition.GetAcquisitionListUseCase
import org.sopt.certi.domain.usecase.activity.GetActivityListUseCase
import org.sopt.certi.domain.usecase.career.GetCareerListUseCase
import org.sopt.certi.domain.usecase.DummyUseCase
import org.sopt.certi.domain.usecase.FavoriteUseCase
import org.sopt.certi.domain.usecase.PreCertEditUseCase
import org.sopt.certi.domain.usecase.PreCertUseCase
import org.sopt.certi.domain.usecase.auth.SearchMajorUseCase
import org.sopt.certi.domain.usecase.auth.SearchUnivUseCase
import org.sopt.certi.domain.usecase.auth.SignInUseCase
import org.sopt.certi.domain.usecase.auth.SignUpUseCase
import org.sopt.certi.domain.usecase.ToggleFavoriteUseCase
import org.sopt.certi.domain.usecase.UserInfoUseCase
import org.sopt.certi.domain.usecase.acquisition.AcquiredCertUseCase
import org.sopt.certi.domain.usecase.acquisition.DeleteAcquisitionUseCase
import org.sopt.certi.domain.usecase.acquisition.GetAcquisitionDetailUseCase
import org.sopt.certi.domain.usecase.activity.DeleteActivityUseCase
import org.sopt.certi.domain.usecase.activity.EditActivityUseCase
import org.sopt.certi.domain.usecase.career.DeleteCareerUseCase
import org.sopt.certi.domain.usecase.career.EditCareerUseCase
import org.sopt.certi.domain.usecase.certification.GetJobCertListUseCase
import org.sopt.certi.domain.usecase.certification.GetCertInfoUseCase
import org.sopt.certi.domain.usecase.certification.GetRecommendCertListUseCase
import org.sopt.certi.domain.usecase.certification.SearchCertListUseCase
import org.sopt.certi.domain.usecase.certification.Top3JobCertListUseCase
import org.sopt.certi.domain.usecase.certification.Top3TrackCertListUseCase
import org.sopt.certi.domain.usecase.comment.DeleteCommentUseCase
import org.sopt.certi.domain.usecase.comment.GetCommentListUseCase
import org.sopt.certi.domain.usecase.comment.LikeCommentUseCase
import org.sopt.certi.domain.usecase.comment.RegisterCommentUseCase
import org.sopt.certi.domain.usecase.precert.AcquireExpectCertUseCase
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
    ): GetJobCertListUseCase = GetJobCertListUseCase(certRepository)

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

    @Provides
    @Singleton
    fun provideAcquiredCertUseCase(
        acquisitionRepository: AcquisitionRepository
    ): AcquiredCertUseCase = AcquiredCertUseCase(acquisitionRepository)

    @Provides
    @Singleton
    fun provideGetCertInfoUseCase(
        certRepository: CertRepository
    ): GetCertInfoUseCase = GetCertInfoUseCase(certRepository)

    @Provides
    @Singleton
    fun provideAcquireExpectCertUseCase(
        preCertRepository: PreCertRepository
    ): AcquireExpectCertUseCase = AcquireExpectCertUseCase(preCertRepository)

    @Provides
    @Singleton
    fun provideGetCareerListUseCase(
        repository: CareerRepository
    ): GetCareerListUseCase = GetCareerListUseCase(repository)

    @Provides
    @Singleton
    fun provideDeleteCareerUseCase(
        repository: CareerRepository
    ): DeleteCareerUseCase = DeleteCareerUseCase(repository)

    @Provides
    @Singleton
    fun provideAddCareerUseCase(
        careerRepository: CareerRepository
    ): AddCareerUseCase = AddCareerUseCase(careerRepository)

    @Provides
    @Singleton
    fun provideEditCareerUseCase(
        careerRepository: CareerRepository
    ): EditCareerUseCase = EditCareerUseCase(careerRepository)

    @Provides
    @Singleton
    fun provideGetAcquisitionListUseCase(
        repository: AcquisitionRepository
    ): GetAcquisitionListUseCase = GetAcquisitionListUseCase(repository)

    @Provides
    @Singleton
    fun provideDeleteAcquisitionUseCase(
        repository: AcquisitionRepository
    ): DeleteAcquisitionUseCase = DeleteAcquisitionUseCase(repository)

    @Provides
    @Singleton
    fun provideGetAcquisitionDetailUseCase(
        repository: AcquisitionRepository
    ): GetAcquisitionDetailUseCase = GetAcquisitionDetailUseCase(repository)

    @Provides
    @Singleton
    fun provideGetActivityListUseCase(
        repository: ActivityRepository
    ): GetActivityListUseCase = GetActivityListUseCase(repository)

    @Provides
    @Singleton
    fun provideDeleteActivityUseCase(
        repository: ActivityRepository
    ): DeleteActivityUseCase = DeleteActivityUseCase((repository))

    @Provides
    @Singleton
    fun provideAddActivityUseCase(
        activityRepository: ActivityRepository
    ): AddActivityUseCase = AddActivityUseCase(activityRepository)

    @Provides
    @Singleton
    fun provideEditActivityUseCase(
        activityRepository: ActivityRepository
    ): EditActivityUseCase = EditActivityUseCase(activityRepository)

    @Provides
    @Singleton
    fun providePreCertEditUseCase(
        preCertEditRepository: PreCertEditRepository
    ): PreCertEditUseCase = PreCertEditUseCase(preCertEditRepository)

    @Provides
    @Singleton
    fun provideTop3TrackCertListUseCase(
        certRepository: CertRepository
    ): Top3TrackCertListUseCase = Top3TrackCertListUseCase(certRepository)

    @Provides
    @Singleton
    fun provideTop3JobCertListUseCase(
        certRepository: CertRepository
    ): Top3JobCertListUseCase = Top3JobCertListUseCase(certRepository)

    @Provides
    @Singleton
    fun provideGetCommentListUseCase(
        commentRepository: CommentRepository
    ): GetCommentListUseCase = GetCommentListUseCase(commentRepository)

    @Provides
    @Singleton
    fun provideRegisterCommentUseCase(
        commentRepository: CommentRepository
    ): RegisterCommentUseCase = RegisterCommentUseCase(commentRepository)

    @Provides
    @Singleton
    fun provideLikeCommentUseCase(
        commentRepository: CommentRepository
    ): LikeCommentUseCase = LikeCommentUseCase(commentRepository)

    @Provides
    @Singleton
    fun provideDeleteCommentUseCase(
        commentRepository: CommentRepository
    ): DeleteCommentUseCase = DeleteCommentUseCase(commentRepository)
}
