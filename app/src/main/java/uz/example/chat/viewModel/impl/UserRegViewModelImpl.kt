package uz.example.chat.viewModel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import uz.example.chat.model.User
import uz.example.chat.usescase.UserRegUsesCase
import uz.example.chat.viewModel.UserRegViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserRegViewModelImpl @Inject constructor(private val usesCaseImpl: UserRegUsesCase) :
    ViewModel(),
    UserRegViewModel {
    override val successSent: MutableLiveData<Unit> = MutableLiveData()
    override val errorMessage: MutableLiveData<String> = MutableLiveData()
    override val number: MutableLiveData<String> = MutableLiveData()
    override val back: MutableLiveData<Unit> = MutableLiveData()

    override fun saveOnClicked(userModel: User) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = usesCaseImpl.sendUserDatas(userModel)
            if (response.isSuccessful && response.body()!!) {
                successSent.postValue(Unit)
                usesCaseImpl.sendUserData(userModel)
            } else
                errorMessage.postValue("${response.errorBody()}")
        }
    }

    override fun onBackClicked() {
        back.postValue(Unit)
    }
}