package uz.example.chat.viewModel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.example.chat.viewModel.RegistrationViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModelImpl @Inject constructor() : ViewModel(), RegistrationViewModel {
    override val action: MutableLiveData<Unit> = MutableLiveData()

    override fun nextClicked() {
        action.value = Unit
    }
}