package uz.example.chat.usescase.impl

import uz.example.chat.model.User
import uz.example.chat.repository.MainRepository
import uz.example.chat.usescase.UserRegUsesCase
import retrofit2.Response
import uz.example.chat.repository.AuthRepository
import javax.inject.Inject

class UserRegUsesCaseImpl @Inject constructor(private val repository: AuthRepository) :
    UserRegUsesCase {


    override fun sendUserData(user: User) {
//        val fullName = user.name + " " + user.lastname
//        repository.setUserName(fullName)
//        repository.setUserNumber(user.name)
//        repository.setUserNumber(user.number)
    }

//    override suspend fun sendUserDatas(): Response<Boolean> =
//        repository.sendUser(user)

    override fun addUserToServer(user: User, onSuccess: (Unit) -> Unit, onError: (String) -> Unit) =
        repository.addUserToServer(user,onSuccess,onError)
}