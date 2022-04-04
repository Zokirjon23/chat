package uz.example.chat.di

import android.content.Context
import androidx.room.Room
import uz.example.chat.db.local.MyDataBase
import uz.example.chat.db.local.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object RoomModule {

    @Singleton
    @Provides
    fun provideMyDatabase(@ApplicationContext context: Context): MyDataBase {
        return Room
            .databaseBuilder(
                context,
                MyDataBase::class.java,
                MyDataBase.DATABASE_NAME
            )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideUserDao(myDataBase: MyDataBase): UserDao {
        return myDataBase.userDao()
    }
}