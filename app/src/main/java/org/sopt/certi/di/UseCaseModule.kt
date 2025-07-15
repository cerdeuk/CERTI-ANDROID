package org.sopt.certi.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.certi.domain.repository.AuthRepository
import org.sopt.certi.domain.repository.DummyRepository
import org.sopt.certi.domain.repository.UserRepository
import org.sopt.certi.domain.usecase.DummyUseCase
import org.sopt.certi.domain.usecase.GetInterestedJobListUseCase
import org.sopt.certi.domain.usecase.SignInUseCase
import org.sopt.certi.domain.usecase.SignUpUseCase
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
    fun provideGetInterestedJobListUseCase(
        userRepository: UserRepository
    ): GetInterestedJobListUseCase = GetInterestedJobListUseCase(userRepository)
}
