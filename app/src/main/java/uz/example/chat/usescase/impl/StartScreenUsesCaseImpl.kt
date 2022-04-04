package uz.example.chat.usescase.impl

import uz.example.chat.repository.MainRepository
import uz.example.chat.usescase.StartScreenUsesCase
import javax.inject.Inject

class StartScreenUsesCaseImpl @Inject constructor(private val repository: MainRepository) :
    StartScreenUsesCase {



    override fun getNumberReg(): Boolean {
        return repository.getUserNumber() == ""
    }

}