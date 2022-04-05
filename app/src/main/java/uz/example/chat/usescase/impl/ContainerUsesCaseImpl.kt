package uz.example.chat.usescase.impl

import com.google.firebase.firestore.auth.User
import uz.example.chat.repository.AuthRepository
import uz.example.chat.usescase.ContainerUsesCase
import javax.inject.Inject

class ContainerUsesCaseImpl @Inject constructor(private val repository: AuthRepository) :
    ContainerUsesCase {

    override fun getCurrentAuth() = repository.getAuth()!!

    override fun logOut() = repository.removeCurrentAuth()
}