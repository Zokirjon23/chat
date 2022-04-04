package uz.example.chat.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import uz.example.chat.R
import uz.example.chat.viewModel.impl.StartScreenViewModelImpl
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StartScreen : Fragment(R.layout.start_screen_fragment) {
    private val viewModel: StartScreenViewModelImpl by viewModels()

    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        viewModel.isRegistration.observe(this, isRegistration)
        findNavController().navigate(R.id.action_startScreen2_to_registrationScreen2)

        Log.d("AAA","Fragment Start")
//        findNavController().navigate(R.id.action_startScreen2_to_containerScreen)
    }

    private val isRegistration = Observer<Boolean> {
        if (it) {
            findNavController().navigate(R.id.action_startScreen2_to_registrationScreen2)
        } else {
            findNavController().navigate(R.id.action_startScreen2_to_containerScreen)
        }
    }
}