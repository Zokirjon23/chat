package uz.example.chat.di

import uz.example.chat.db.local.MyPreferences
import uz.example.chat.db.local.UserDao
import uz.example.chat.db.remote.UserService
import uz.example.chat.repository.MainRepository
import uz.example.chat.repository.MainRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMainRepository(
        myPreferences: MyPreferences,
        userClient: UserService,
        userDao: UserDao
    ): MainRepository {
        return MainRepositoryImpl(myPreferences, userClient,userDao)
    }
}