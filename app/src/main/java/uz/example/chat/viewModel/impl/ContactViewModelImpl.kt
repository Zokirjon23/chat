package uz.example.chat.viewModel.impl

import android.content.ContentResolver
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uz.example.chat.model.User
import uz.example.chat.usescase.ContactUsesCase
import uz.example.chat.viewModel.ContactViewModel
import javax.inject.Inject

@HiltViewModel
class ContactViewModelImpl @Inject constructor(private val usesCase: ContactUsesCase) :
    ViewModel(), ContactViewModel {
    override val contacts: MutableLiveData<ArrayList<User>> = MutableLiveData()
    override val back: MutableLiveData<Unit> = MutableLiveData()

    private val _permission = MutableLiveData<Unit>()
    override val permission: LiveData<Unit> = _permission

    private val _progressbar = MutableLiveData<Boolean>()
    override val progressbar: LiveData<Boolean> = _progressbar

    private val _error = MutableLiveData<String>()
    override val error : LiveData<String> = _error

    init {
        _permission.value = Unit
    }

    override fun onCreatedView(contentResolver: ContentResolver, net: Boolean) {
        _progressbar.value = true
        viewModelScope.launch(Dispatchers.IO) {
            val deviceContact = usesCase.readContact(contentResolver)
            usesCase.getAllContactServer(
                onSuccess = {
                    Log.d("AAA", it.size().toString())
                    contacts.postValue(usesCase.findContacts(deviceContact,it))
                    _progressbar.postValue(false)
                },
                onError = {
                    _progressbar.postValue(false)
                    _error.postValue(it)
                })
        }
    }


    override fun onBackClicked() {
        back.postValue(Unit)
    }
}

