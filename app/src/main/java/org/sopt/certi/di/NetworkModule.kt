package org.sopt.certi.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.sopt.certi.BuildConfig
import org.sopt.certi.core.network.TokenManager
import retrofit2.Retrofit
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @OptIn(ExperimentalSerializationApi::class)
    @Provides
    @Singleton
    fun providesJson(): Json =
        Json {
            isLenient = true
            prettyPrint = true
            explicitNulls = false
            ignoreUnknownKeys = true
        }

    @Provides
    @Singleton
    fun providesOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        authorizationInterceptor: okhttp3.Interceptor
    ): OkHttpClient =
        OkHttpClient.Builder().apply {
            connectTimeout(10, TimeUnit.SECONDS)
            writeTimeout(10, TimeUnit.SECONDS)
            readTimeout(10, TimeUnit.SECONDS)
            addInterceptor(loggingInterceptor)
            addInterceptor(authorizationInterceptor)
            addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("Accept", "*/*")
                    .build()
                chain.proceed(request)
            }
        }.build()

    @Provides
    @Singleton
    fun providesAuthorizationInterceptor(
        tokenManager: TokenManager
    ): okhttp3.Interceptor = okhttp3.Interceptor { chain ->
        val request = chain.request()
        val url = request.url.toString()

        if (!shouldAddAuthorization(url)) {
            return@Interceptor chain.proceed(request)
        }

        val token = tokenManager.getToken()

        val newRequest = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer $token")
            .build()
        chain.proceed(newRequest)
    }

    private fun shouldAddAuthorization(url: String): Boolean {
        return !url.contains("/api/v1/auth/sign-in") && !url.contains("/api/v1/auth/sign-up") &&
            !url.contains("/api/v1/university/search") && !url.contains("/api/v1/major/search")
    }

    @Provides
    @Singleton
    fun providesLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @ExperimentalSerializationApi
    @Provides
    @Singleton
    fun providesRetrofit(
        okHttpClient: OkHttpClient,
        json: Json
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(
                json.asConverterFactory(requireNotNull("application/json".toMediaTypeOrNull()))
            )
            .build()

    @Provides
    @Singleton
    @Named("S3Client") // 이름표 붙임
    fun providesS3OkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient =
        OkHttpClient.Builder().apply {
            connectTimeout(20, TimeUnit.SECONDS)
            writeTimeout(20, TimeUnit.SECONDS)
            readTimeout(20, TimeUnit.SECONDS)
            addInterceptor(loggingInterceptor)
        }.build()

    @Provides
    @Singleton
    @Named("S3Retrofit")
    fun provideS3Retrofit(
        @Named("S3Client") okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .build()
    }
}
