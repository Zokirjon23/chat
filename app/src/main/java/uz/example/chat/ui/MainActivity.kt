package uz.example.chat.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import uz.example.chat.R
import dagger.hilt.android.AndroidEntryPoint
import uz.example.chat.util.internetChangeListener

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var listener : ((Boolean)->Unit)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        internetChangeListener().observe(this,internetObserver)
    }

//    private val internetObserver = Observer<Boolean>{
//        Log.d("VVV",it.toString())
//        listener.invoke(it)
//    }
//
//    fun internetLister(l : (Boolean) -> Unit){
//        listener = l
//    }
}