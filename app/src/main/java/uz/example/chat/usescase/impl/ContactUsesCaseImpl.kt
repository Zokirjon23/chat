package uz.example.chat.usescase.impl

import android.content.ContentResolver
import android.database.Cursor
import android.provider.ContactsContract
import android.util.Log
import com.google.firebase.firestore.QuerySnapshot
import uz.example.chat.model.User
import uz.example.chat.repository.AuthRepository
import uz.example.chat.usescase.ContactUsesCase
import javax.inject.Inject


class ContactUsesCaseImpl @Inject constructor(private val repository: AuthRepository) :
    ContactUsesCase {

    override fun readContact(getContentResolver: ContentResolver): ArrayList<User> {
        val list = ArrayList<User>()
        val phones: Cursor = getContentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null
        )!!
        while (phones.moveToNext()) {
            val a = phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
            val b = phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
            list.add(
                User(
                    0,
                    phones.getString(b),
                    phones.getString(a),
                    ""
                )
            )
        }
        phones.close()
        return list
    }

//    override  fun getAllContacts(users: ArrayList<User>): Response<List<User>> =
//        repositorusers: ArrayList<User>y.sendAllContact(users)

//    override  fun getContacts() =
//         repository.getAppContacts()

    override fun findContacts(contacts: ArrayList<User>, users: QuerySnapshot): ArrayList<User> {
        val list = ArrayList<User>()
        for (i in users) {
           val user = i.toObject(User::class.java)
            for(contact in contacts){
                Log.d("VVV","user ${user.number}")
                Log.d("VVV","contact ${contact.number}")
                if (contact.number == user.number){
                    Log.d("VVV","contact exsist ${contact.number}")
                    list.add(contact)
                    break
                }
            }
        }
        return list
    }

    override fun getAllContactServer(
        onSuccess: (QuerySnapshot) -> Unit,
        onError: (String) -> Unit
    ) = repository.getUsersServer(onSuccess, onError)
}