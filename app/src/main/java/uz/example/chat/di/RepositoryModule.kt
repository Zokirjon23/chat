package uz.example.chat.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.firestore.FirebaseFirestore
import uz.example.chat.db.local.MyPreferences
import uz.example.chat.db.local.UserDao
import uz.example.chat.db.remote.UserService
import uz.example.chat.repository.MainRepository
import uz.example.chat.repository.MainRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.example.chat.repository.AuthRepository
import uz.example.chat.repository.AuthRepositoryImpl
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

    @Singleton
    @Provides
    fun provideAuthRepository(firebaseAuth: FirebaseAuth, fireStore: FirebaseFirestore,pref : MyPreferences) : AuthRepository{
        return AuthRepositoryImpl(fireStore,firebaseAuth,pref)
    }
}