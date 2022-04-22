package dev.theimpulson.movies.api

import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [TMDBModule::class]
)
class MockTMDBModule : TMDBModule() {
    override var baseURL = "http://localhost:8080/"
}
