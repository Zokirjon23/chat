package uz.example.chat.viewModel

import androidx.lifecycle.LiveData
import uz.example.chat.model.User

interface ContainerViewModel {
    fun logOutClicked()

    val navToRegistration : LiveData<Unit>
    val userLiveData : LiveData<User>
}