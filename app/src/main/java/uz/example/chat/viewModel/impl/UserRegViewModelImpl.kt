package uz.example.chat.viewModel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uz.example.chat.model.User
import uz.example.chat.usescase.UserRegUsesCase
import uz.example.chat.viewModel.UserRegViewModel
import javax.inject.Inject

@HiltViewModel
class UserRegViewModelImpl @Inject constructor(private val usesCase: UserRegUsesCase) :
    ViewModel(),
    UserRegViewModel {
    override val successSent: MutableLiveData<Unit> = MutableLiveData()
    override val errorMessage: MutableLiveData<String> = MutableLiveData()
    override val number: MutableLiveData<String> = MutableLiveData()
    override val back: MutableLiveData<Unit> = MutableLiveData()
    override val progressbar: MutableLiveData<Boolean> = MutableLiveData()

    override fun saveOnClicked(userModel: User) {
        progressbar.value = true
        viewModelScope.launch(Dispatchers.IO) {
            usesCase.addUserToServer(user = userModel,
                onSuccess = {
                    progressbar.postValue(false)
                    successSent.postValue(Unit)
                },
                onError = {
                    progressbar.postValue(false)
                    errorMessage.postValue(it)
                })
        }
    }

    override fun onBackClicked() {
        back.postValue(Unit)
    }
}