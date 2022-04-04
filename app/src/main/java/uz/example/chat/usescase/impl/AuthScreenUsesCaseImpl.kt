package uz.example.chat.usescase.impl

import uz.example.chat.repository.MainRepository
import uz.example.chat.usescase.AuthScreenUsescase
import javax.inject.Inject


class AuthScreenUsesCaseImpl @Inject constructor(private val repositoryFactory: MainRepository) :
    AuthScreenUsescase {

}