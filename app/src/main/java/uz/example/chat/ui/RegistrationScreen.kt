package uz.example.chat.ui

import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.os.bundleOf
import uz.example.chat.R
import uz.example.chat.databinding.FragmentRegistrationScreenBinding
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import uz.example.chat.viewModel.impl.RegistrationViewModelImpl
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RegistrationScreen : Fragment(R.layout.fragment_registration_screen) {

    private lateinit var binding: FragmentRegistrationScreenBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentRegistrationScreenBinding.bind(view)
        binding.title.setText(R.string.Registration)
        val viewModel: RegistrationViewModelImpl by viewModels()

        binding.next.setOnClickListener {
            if (binding.editNumber.text.toString().length < 17) {
                Toast.makeText(requireContext(), "Error number", Toast.LENGTH_SHORT).show()
            } else {
                val bundle = bundleOf("number" to binding.editNumber.text.toString())
                findNavController()
                    .navigate(R.id.action_registrationScreen2_to_authScreen2, bundle)
            }
        }

        binding.editNumber.setOnEditorActionListener { _, actionId, event ->
            if (event != null && event.keyCode == KeyEvent.KEYCODE_ENTER || actionId == EditorInfo.IME_ACTION_DONE) {
                viewModel.nextClicked()
            }
            false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                activity?.finish()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
    }

}

