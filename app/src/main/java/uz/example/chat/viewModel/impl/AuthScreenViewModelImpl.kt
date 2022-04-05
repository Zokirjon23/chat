package uz.example.chat.viewModel.impl

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.PhoneAuthOptions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uz.example.chat.usescase.AuthScreenUsescase
import uz.example.chat.viewModel.AuthScreenViewModel
import javax.inject.Inject

@HiltViewModel
class AuthScreenViewModelImpl @Inject constructor(private val usesCase: AuthScreenUsescase) :
    ViewModel(),
    AuthScreenViewModel {

    private val _back = MutableLiveData<Unit>()
    private val _error = MutableLiveData<String>()
    private val _progressbar = MutableLiveData<Boolean>()
    private val _navToUserReg = MutableLiveData<Unit>()
    override val navToUserReg: LiveData<Unit> = _navToUserReg
    private val _navToDashboard = MutableLiveData<Unit>()
    override val navToDashboard: LiveData<Unit> = _navToDashboard
    override val back: LiveData<Unit> = _back
    override val error: LiveData<String> = _error
    override val progressbar: LiveData<Boolean> = _progressbar

    override fun onCreated(number: String, b: PhoneAuthOptions.Builder, network: Boolean) {
        if (!network) {
            _error.value = "Connect to Internet"
            return
        }

        viewModelScope.launch(Dispatchers.IO) {
            usesCase.sendSms(builder = b,
                onSent = {
                    Log.d("DDD", "onSent $it")
                },
                onVerify = {
                    Log.d("DDD", "onVerify $it")
                    usesCase.checkAuth(phoneNumber = number,
                        onSuccess = { isExist ->
                            Log.d("ZZZ", isExist.toString())
                            if (isExist) {
                                _navToDashboard.postValue(Unit)
                            } else {
                                _navToUserReg.postValue(Unit)
                            }
                        },
                        onError = { error ->
                            _error.postValue(error)
                        })
                    _navToUserReg.postValue(Unit)
                },
                onError = {
                    _error.postValue(it)
                })
        }
    }

    override fun nextClicked(number: String, inputCode: String, network: Boolean) {
        if (!network) {
            _error.value = "Connect to Internet"
            return
        }

        if (inputCode.isEmpty() || inputCode.length != 6) {
            _error.value = "Enter code"
        } else {
            _progressbar.value = true
            usesCase.checkCode(smsCode = inputCode,
                onComplete = { _ ->
                    usesCase.checkAuth(phoneNumber = number,
                        onSuccess = { isExist ->
                            _progressbar.value = false
                            Log.d("ZZZ", isExist.toString())
                            if (isExist) {
                                _navToDashboard.postValue(Unit)
                            } else {
                                _navToUserReg.postValue(Unit)
                            }
                        },
                        onError = {
                            _progressbar.value = false
                            _error.postValue(it)
                        })
                },
                onError = { errorMessage ->
                    _progressbar.value = false
                    _error.value = errorMessage
                })
        }
    }

    override fun backClicked() {
        _back.value = Unit
    }

}
