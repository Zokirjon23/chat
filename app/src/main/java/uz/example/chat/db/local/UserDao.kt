package uz.example.chat.db.local

import androidx.room.*
import uz.example.chat.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list: List<User>)

    @Insert
    fun insert(user: User)

    @Query("SELECT * FROM user")
    fun getAllContact(): Flow<List<User>>

    @Query("DELETE FROM user")
    fun deleteAll()

    @Delete
    fun delete(user: User)
}