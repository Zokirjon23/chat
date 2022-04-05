package uz.example.chat.usescase

import uz.example.chat.model.User


interface ContainerUsesCase {
    fun getCurrentAuth(): User
    fun logOut()
}