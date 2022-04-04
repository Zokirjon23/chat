package uz.example.chat.usescase

import uz.example.chat.model.User

interface DashboardUsesCase {

    fun getUser(): User
    // get user from preference

}