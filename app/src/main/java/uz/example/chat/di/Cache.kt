package uz.example.chat.di

import android.content.Context
import android.content.SharedPreferences
import uz.example.chat.db.local.MyPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object Cache {

    @Singleton
    @Provides
    fun providePreference(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("Keys", Context.MODE_PRIVATE)
    }

    @Singleton
    @Provides
    fun provideMyPreference(sharedPreferences: SharedPreferences) : MyPreferences{
        return MyPreferences(sharedPreferences)
    }
}