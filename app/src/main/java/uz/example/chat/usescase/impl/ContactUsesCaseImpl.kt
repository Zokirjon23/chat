package uz.example.chat.usescase.impl

import android.database.Cursor
import uz.example.chat.usescase.ContactUsesCase
import javax.inject.Inject
import android.provider.ContactsContract
import android.content.ContentResolver
import uz.example.chat.model.User
import uz.example.chat.repository.MainRepository
import retrofit2.Response


class ContactUsesCaseImpl @Inject constructor(private val repository: MainRepository) :
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

    override suspend fun getAllContacts(users: ArrayList<User>): Response<List<User>> =
        repository.sendAllContact(users)

    override suspend fun getContacts() =
         repository.getAppContacts()

    override suspend fun insertContacts(list: List<User>) {
//        repository.getUserContacts().collect {
//            if (!list.containsAll(it)) {
                repository.insertAppContacts(list)
//            }
//        }
    }
}