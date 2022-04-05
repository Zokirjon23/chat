package uz.example.chat.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.example.chat.R
import uz.example.chat.databinding.FragmentRegistrationScreenBinding
import uz.example.chat.util.internetChangeListener
import uz.example.chat.viewModel.impl.RegistrationViewModelImpl


@AndroidEntryPoint
class RegistrationScreen : Fragment(R.layout.fragment_registration_screen) {

    private val binding: FragmentRegistrationScreenBinding by viewBinding()
    private val viewModel: RegistrationViewModelImpl by viewModels()

    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.title.setText(R.string.Registration)

        binding.next.setOnClickListener { viewModel.nextClicked(binding.editNumber.text.toString()) }

        binding.editNumber.setOnEditorActionListener { _, actionId, event ->
            if (event != null && event.keyCode == KeyEvent.KEYCODE_ENTER || actionId == EditorInfo.IME_ACTION_DONE) {
                viewModel.nextClicked(binding.editNumber.text.toString())
            }
            true
        }

        internetChangeListener().observe(this, internetObserver)
        viewModel.navToAuth.observe(this, nextObserver)
        viewModel.error.observe(this, errorObserver)
    }

    private val internetObserver = Observer<Boolean> {
        Log.d("SSS",it.toString())
        binding.title.text = getString(
            if (it) R.string.Registration
            else R.string.connection
        )
    }

    private val nextObserver = Observer<String> {
        findNavController().navigate(
            R.id.action_registrationScreen2_to_authScreen2, bundleOf("numberPhone" to it)
        )
    }
    private val errorObserver = Observer<String> {
        Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
    }

}

