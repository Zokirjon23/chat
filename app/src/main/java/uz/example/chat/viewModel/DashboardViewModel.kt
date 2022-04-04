package uz.example.chat.viewModel

import androidx.lifecycle.LiveData
import uz.example.chat.model.User

interface DashboardViewModel {
    val user: LiveData<User>

    fun onCreated()
    // get User from usesCase

}