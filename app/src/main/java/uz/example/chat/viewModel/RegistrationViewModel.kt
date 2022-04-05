package uz.example.chat.viewModel

import androidx.lifecycle.LiveData

interface RegistrationViewModel {
    val navToAuth : LiveData<String>
    val error : LiveData<String>

    fun nextClicked(num : String)

}