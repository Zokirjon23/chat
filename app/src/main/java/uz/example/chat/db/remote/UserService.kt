package uz.example.chat.db.remote

import uz.example.chat.model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {

    @POST("/userdata")
    suspend fun sendUser(@Body user: User): Response<Boolean>

    @POST("/contacts")
    suspend fun sendContacts(@Body list: List<User>): Response<List<User>>
}