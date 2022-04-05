package uz.example.chat.usescase.impl

import com.google.firebase.auth.PhoneAuthOptions
import uz.example.chat.repository.AuthRepository
import uz.example.chat.usescase.AuthScreenUsescase
import javax.inject.Inject


class AuthScreenUsesCaseImpl @Inject constructor(private val authRepository: AuthRepository) :
    AuthScreenUsescase {


    override fun sendSms(
        builder: PhoneAuthOptions.Builder,
        onSent: (String) -> Unit,
        onVerify: (String) -> Unit,
        onError: (String) -> Unit
    ) = authRepository.sendSms(builder, onSent, onVerify, onError)

    override fun checkCode(smsCode: String, onComplete: (Unit) -> Unit, onError: (String) -> Unit) =
        authRepository.checkCode(smsCode,onComplete,onError)

    override fun checkAuth(
        phoneNumber: String,
        onSuccess: (Boolean) -> Unit,
        onError: (String) -> Unit
    ) = authRepository.checkNumberExist(phoneNumber,onSuccess,onError)
}