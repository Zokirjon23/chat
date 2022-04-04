package uz.example.chat.usescase.impl

import uz.example.chat.model.User
import uz.example.chat.repository.MainRepository
import uz.example.chat.usescase.DashboardUsesCase
import javax.inject.Inject

class DashboardUsesCaseImpl @Inject constructor(private val repository: MainRepository) :
    DashboardUsesCase {

    override fun getUser(): User {
        return User(
            repository.getUserId(),
            repository.getUserNumber(),
            repository.getUserName(),
            ""
        )
    }

}