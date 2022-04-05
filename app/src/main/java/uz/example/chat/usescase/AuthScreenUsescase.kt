package uz.example.chat.usescase

import com.google.firebase.auth.PhoneAuthOptions

interface AuthScreenUsescase {

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

    // check number exist in server
    fun checkAuth(  phoneNumber: String,
                    onSuccess: (Boolean) -> Unit,
                    onError: (String) -> Unit)

}