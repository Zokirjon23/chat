package uz.example.chat.db.local

import android.content.SharedPreferences
import javax.inject.Inject

class MyPreferences @Inject constructor(private val shp: SharedPreferences) {
    private val editor: SharedPreferences.Editor = shp.edit()

    fun setUserNumber(string: String) {
        editor.putString("number", string).apply()
    }

    fun getUserNumber(): String {
        return shp.getString("number", "")!!
    }

    fun setUserName(userName: String) {
        editor.putString("username", userName).apply()
    }

    fun getUserName(): String {
        return shp.getString("username", "")!!
    }

    fun getUserId(): Long {
        return shp.getLong("userId", 0)
    }

    fun setUserId(id: Long) {
        editor.putLong("UserId", id)
    }
}