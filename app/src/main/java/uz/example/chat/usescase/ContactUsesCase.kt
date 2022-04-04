package uz.example.chat.usescase

import android.content.ContentResolver
import uz.example.chat.model.User
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface ContactUsesCase {
    fun readContact(getContentResolver: ContentResolver): ArrayList<User>
    // read all contacts from device

    suspend fun getAllContacts(users: ArrayList<User>): Response<List<User>>
    // get app user my contacts

    suspend fun getContacts(): Flow<List<User>>
    // get app user my contacts from room

    suspend fun insertContacts(list: List<User>)
    // insert app user my contacts from room

}