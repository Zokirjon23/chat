package uz.example.chat.usescase

import android.content.ContentResolver
import com.google.firebase.firestore.QuerySnapshot
import uz.example.chat.model.User

interface ContactUsesCase {
    fun readContact(getContentResolver: ContentResolver): ArrayList<User>
    // read all contacts from device

//    fun getAllContacts(users: ArrayList<User>): Response<List<User>>
    // get app user my contacts

//    fun getContacts(): Flow<List<User>>
    // get app user my contacts from room

//    fun insertContacts(list: List<User>)
    // insert app user my contacts from room

    /*
    find my contacts is registered
     */
    fun findContacts(contacts : ArrayList<User>,users : QuerySnapshot) : ArrayList<User>

    fun getAllContactServer(onSuccess: (QuerySnapshot) -> Unit,
                            onError: (String) -> Unit)
}