package uz.example.chat.usescase.impl

import android.util.Log
import uz.example.chat.repository.AuthRepository
import uz.example.chat.usescase.StartScreenUsesCase
import javax.inject.Inject

class StartScreenUsesCaseImpl @Inject constructor(private val repository: AuthRepository) :
    StartScreenUsesCase {



    override fun getNumberReg(): Boolean {
        Log.d("DDD",repository.getAuth().toString())
        return repository.getAuth() == null
    }

}