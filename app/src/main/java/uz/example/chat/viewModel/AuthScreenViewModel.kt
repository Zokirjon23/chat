package uz.example.chat.viewModel

import androidx.lifecycle.LiveData

interface AuthScreenViewModel {
    val next: LiveData<Unit>
    val back: LiveData<Unit>
    
    fun nextClicked()
    // check code editText isEmpty and signInWithPhoneAuthCredential

    fun backClicked()

}