package uz.example.chat.viewModel.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uz.example.chat.model.User
import uz.example.chat.usescase.ContainerUsesCase
import uz.example.chat.viewModel.ContainerViewModel
import javax.inject.Inject

@HiltViewModel
class ContainerViewModelImpl @Inject constructor(private val usesCase: ContainerUsesCase) :
    ContainerViewModel, ViewModel() {
    private val _navToReg = MutableLiveData<Unit>()
    override val navToRegistration: LiveData<Unit> = _navToReg

    private val _userLiveData = MutableLiveData<User>()
    override val userLiveData: LiveData<User> = _userLiveData

    init {
            _userLiveData.value = usesCase.getCurrentAuth()
    }

    override fun logOutClicked() {
        usesCase.logOut()
        _navToReg.value = Unit
    }
}