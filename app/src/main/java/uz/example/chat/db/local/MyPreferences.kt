package uz.example.chat.db.local

import android.content.SharedPreferences
import com.google.gson.Gson
import uz.example.chat.model.User
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

    fun setCurrentAuth(user: User) {
        val gson = Gson()
        val json = gson.toJson(user)
        editor.putString("user", json).apply()
    }

    fun getCurrentAuth(): User? {
        val json = shp.getString("user", null)
        val gson = Gson()
        return gson.fromJson(json,User::class.java)
    }

    fun removeAuth(){
        editor.remove("user").apply()
    }
}