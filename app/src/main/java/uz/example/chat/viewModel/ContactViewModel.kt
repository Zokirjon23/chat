package uz.example.chat.viewModel

import android.content.ContentResolver
import androidx.lifecycle.LiveData
import uz.example.chat.model.User

interface ContactViewModel {
    val contacts : LiveData<ArrayList<User>>
    val back : LiveData<Unit>
    val permission : LiveData<Unit>
    fun onCreatedView(contentResolver: ContentResolver,net : Boolean)
    fun onBackClicked()
}