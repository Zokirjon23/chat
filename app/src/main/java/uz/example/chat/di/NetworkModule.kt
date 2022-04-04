package uz.example.chat.di

import com.google.firebase.auth.FirebaseAuth
import uz.example.chat.db.remote.UserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl("https://android-chat-server-1.herokuapp.com")
            .addConverterFactory(GsonConverterFactory.create())
    }

    @Singleton
    @Provides
    fun providesUserService(retrofit: Retrofit.Builder): UserService {
        return retrofit.build().create(UserService::class.java)
    }

    @Singleton
    @Provides
    fun provideAuth() : FirebaseAuth{
        return FirebaseAuth.getInstance()
    }


}