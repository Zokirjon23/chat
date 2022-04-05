package uz.example.chat.viewModel

import androidx.lifecycle.LiveData
import com.google.firebase.auth.PhoneAuthOptions

interface AuthScreenViewModel {
    val navToUserReg: LiveData<Unit>
    val back: LiveData<Unit>
    val error : LiveData<String>
    val progressbar : LiveData<Boolean>
    
    fun nextClicked(number: String,inputCode : String,network : Boolean)
    // check code editText isEmpty and signInWithPhoneAuthCredential

    fun backClicked()

    fun onCreated(number : String,b : PhoneAuthOptions.Builder,network : Boolean)
    val navToDashboard: LiveData<Unit>
}