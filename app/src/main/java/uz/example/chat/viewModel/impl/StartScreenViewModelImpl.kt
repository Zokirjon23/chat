package uz.example.chat.viewModel.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.example.chat.usescase.StartScreenUsesCase
import uz.example.chat.viewModel.StartScreenViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StartScreenViewModelImpl @Inject constructor(usesCase: StartScreenUsesCase) :
    ViewModel(), StartScreenViewModel {
    private val _isRegistration = MutableLiveData<Boolean>()
    override val isRegistration: LiveData<Boolean> = _isRegistration

    init {
        _isRegistration.value = usesCase.getNumberReg()
    }
}