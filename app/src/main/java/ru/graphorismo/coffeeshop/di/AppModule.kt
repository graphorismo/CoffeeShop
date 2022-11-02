package ru.graphorismo.coffeeshop.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.graphorismo.coffeeshop.data.remote.CoffeeShopApi
import ru.graphorismo.coffeeshop.data.repositories.AuthRepository

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideAuthRepository(coffeeShopApi: CoffeeShopApi) : AuthRepository{
        return AuthRepository(coffeeShopApi)
    }

    @Provides
    fun provideCoffeeShopApi(): CoffeeShopApi{
        var retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8100/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(CoffeeShopApi::class.java)
    }
}