package uz.example.chat.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import uz.example.chat.model.User

interface UserRegViewModel {
    val successSent: LiveData<Unit>
    val errorMessage: LiveData<String>
    val number: LiveData<String>
    val back : LiveData<Unit>

    fun saveOnClicked(userModel: User)
    // set name and number
    fun onBackClicked()
    val progressbar: MutableLiveData<Boolean>
}