package uz.example.chat.viewModel.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.example.chat.viewModel.RegistrationViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModelImpl @Inject constructor() : ViewModel(), RegistrationViewModel {
    private val _navToAuth  = MutableLiveData<String>()
    override val navToAuth: LiveData<String> = _navToAuth

    private val _error = MutableLiveData<String>()
    override val error: LiveData<String>  = _error


    override fun nextClicked(num : String) {
        if (num.length < 18) {
            _navToAuth.value = num

        }else{
            _error.value = "Enter number correctly"
        }
    }
}