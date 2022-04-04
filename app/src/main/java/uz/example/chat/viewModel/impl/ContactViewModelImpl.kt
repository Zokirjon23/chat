package uz.example.chat.viewModel.impl

import android.content.ContentResolver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import uz.example.chat.model.User
import uz.example.chat.usescase.ContactUsesCase
import uz.example.chat.viewModel.ContactViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactViewModelImpl @Inject constructor(private val usesCase: ContactUsesCase) :
    ViewModel(), ContactViewModel {
    override val contacts: MutableLiveData<ArrayList<User>> = MutableLiveData()
    override val back: MutableLiveData<Unit> = MutableLiveData()
    private val _permission = MutableLiveData<Unit>()
    override val permission: LiveData<Unit> = _permission

    init {
        _permission.value = Unit
    }

    override fun onCreatedView(contentResolver: ContentResolver, net: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
//            usesCase.getContacts().collect {
//                if (it.isNotEmpty()) {
//                    contacts.postValue(it as ArrayList<User>)
//                }
//            }
            contacts.postValue(usesCase.readContact(contentResolver))
        }
//        if (net) {
//            val list = usesCase.readContact(contentResolver)
//
//            viewModelScope.launch(Dispatchers.IO) {
//                val response = usesCase.getAllContacts(list)
//                if (response.isSuccessful) {
//                    usesCase.getContacts().collect {
//                        if (it.size != response.body()!!.size) {
//                            usesCase.insertContacts(response.body()!!)
//                            contacts.postValue(response.body() as ArrayList<User>)
//                        }
//                    }
//                }
//            }
//        }
    }

    override fun onBackClicked() {
        back.postValue(Unit)
    }
}

