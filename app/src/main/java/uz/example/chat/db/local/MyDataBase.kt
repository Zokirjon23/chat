package uz.example.chat.db.local

import androidx.room.Database
import androidx.room.RoomDatabase
import uz.example.chat.model.User

@Database(entities = [User::class], version = 1)
abstract class MyDataBase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        val DATABASE_NAME: String = "user_db"
    }
}