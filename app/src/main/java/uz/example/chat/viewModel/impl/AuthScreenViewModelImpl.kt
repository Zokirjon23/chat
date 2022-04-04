package uz.example.chat.viewModel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.example.chat.usescase.AuthScreenUsescase
import uz.example.chat.viewModel.AuthScreenViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthScreenViewModelImpl @Inject constructor(private val usesCase: AuthScreenUsescase) :
    ViewModel(),
    AuthScreenViewModel {


    override val next: MutableLiveData<Unit> = MutableLiveData()
    override val back: MutableLiveData<Unit> = MutableLiveData()

    override fun nextClicked() {
        next.postValue(Unit)
    }

    override fun backClicked() {
        back.postValue(Unit)
    }

}
