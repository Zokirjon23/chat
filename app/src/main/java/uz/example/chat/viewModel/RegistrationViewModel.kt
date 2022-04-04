package uz.example.chat.viewModel

import androidx.lifecycle.LiveData

interface RegistrationViewModel {
    val action : LiveData<Unit>


    fun nextClicked()
}