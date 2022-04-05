package uz.example.chat.repository

import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.firestore.QuerySnapshot
import io.michaelrocks.libphonenumber.android.Phonenumber
import uz.example.chat.model.User

interface AuthRepository {

    fun sendSms(
        builder: PhoneAuthOptions.Builder,
        onSent: (String) -> Unit,
        onVerify: (String) -> Unit,
        onError: (String) -> Unit
    )

    fun checkCode(
        smsCode : String,
        onComplete: (Unit) -> Unit,
        onError: (String) -> Unit
    )


     fun addUserToServer (user: User,onSuccess : (Unit) ->Unit,onError : (String) ->Unit)


     fun getAuth() : User?

     fun removeCurrentAuth()

    fun getUsersServer(
        onSuccess: (QuerySnapshot) -> Unit,
        onError: (String) -> Unit
    )

    fun checkNumberExist(
        phoneNumber: String,
        onSuccess: (Boolean) -> Unit,
        onError: (String) -> Unit
    )
}