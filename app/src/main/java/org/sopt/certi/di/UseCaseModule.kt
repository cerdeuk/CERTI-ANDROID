package org.sopt.certi.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.certi.domain.repository.AuthRepository
import javax.inject.Singleton
import org.sopt.certi.domain.repository.DummyRepository
import org.sopt.certi.domain.usecase.DummyUseCase
import org.sopt.certi.domain.usecase.SearchMajorUseCase
import org.sopt.certi.domain.usecase.SearchUnivUseCase
import org.sopt.certi.domain.usecase.SignInUseCase
import org.sopt.certi.domain.usecase.SignUpUseCase

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
}
