package uz.example.chat.usescase.impl

import uz.example.chat.model.User
import uz.example.chat.repository.MainRepository
import uz.example.chat.usescase.UserRegUsesCase
import retrofit2.Response
import javax.inject.Inject

class UserRegUsesCaseImpl @Inject constructor(private val repository: MainRepository) :
    UserRegUsesCase {


    override fun sendUserData(user: User) {
        val fullName = user.name + " " + user.lastname
        repository.setUserName(fullName)
        repository.setUserNumber(user.name)
        repository.setUserNumber(user.number)
    }

    override suspend fun sendUserDatas(user: User): Response<Boolean> =
        repository.sendUser(user)
}