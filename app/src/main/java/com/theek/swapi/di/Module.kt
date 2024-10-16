package com.theek.swapi.di

import com.theek.swapi.data.remote.SwapiService
import com.theek.swapi.data.repository.AllPlanetsRepositoryImpl
import com.theek.swapi.domain.repository.AllPlanetsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Module {

    @Provides
    @Singleton
    fun provideKtorClient(): HttpClient {
        return HttpClient(Android) {
            engine {
                socketTimeout = 15_000
                connectTimeout = 15_000
            }

            install(ContentNegotiation) {
                json(
                    Json {
                        isLenient = true
                        prettyPrint = true
                    }
                )
            }

            install(Logging) {
                logger = Logger.ANDROID
            }
        }
    }

    @Provides
    @Singleton
    fun provideSwapiService(httpClient: HttpClient): SwapiService {
        return SwapiService(httpClient)
    }

    @Provides
    @Singleton
    fun provideAllPlanetsRepository(swapiService: SwapiService): AllPlanetsRepository {
        return AllPlanetsRepositoryImpl(swapiService)
    }
}