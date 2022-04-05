package uz.example.chat.usescase

import uz.example.chat.model.User
import retrofit2.Response

interface UserRegUsesCase {
    fun sendUserData(user: User)

    // send user registration data to the server
//     fun sendUserDatas(user: User): Response<Boolean>

    fun addUserToServer (user: User,onSuccess : (Unit) ->Unit,onError : (String) ->Unit)

}