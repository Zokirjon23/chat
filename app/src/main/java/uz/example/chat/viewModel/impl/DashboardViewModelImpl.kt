package uz.example.chat.viewModel.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.example.chat.model.User
import uz.example.chat.usescase.DashboardUsesCase
import uz.example.chat.viewModel.DashboardViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashboardViewModelImpl @Inject constructor(private val usesCase: DashboardUsesCase) :
    ViewModel(),
    DashboardViewModel {
    private val _user: MutableLiveData<User> = MutableLiveData()
    override val user: LiveData<User> get() = _user

    override fun onCreated() {
        _user.postValue(usesCase.getUser())
    }

}