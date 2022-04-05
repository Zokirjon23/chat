package uz.example.chat.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import uz.example.chat.R
import uz.example.chat.databinding.FragmentAuthScreenBinding
import uz.example.chat.viewModel.impl.AuthScreenViewModelImpl
import com.google.firebase.auth.*
import androidx.core.view.get
import androidx.lifecycle.Observer
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.example.chat.util.isNetworkAvailable
import dagger.hilt.android.AndroidEntryPoint
import uz.example.chat.util.internetChangeListener

@AndroidEntryPoint
class AuthScreen : Fragment(R.layout.fragment_auth_screen) {

    private val binding: FragmentAuthScreenBinding by viewBinding()
    private lateinit var phoneNumber: String
    private val viewModel: AuthScreenViewModelImpl by viewModels()
    private lateinit var builder : PhoneAuthOptions.Builder

    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.toolbar.title.setText(R.string.Authentication)
        phoneNumber = requireArguments().getString("numberPhone")!!

        builder = PhoneAuthOptions.newBuilder()
            .setActivity(requireActivity())
            .setPhoneNumber(phoneNumber)

        binding.toolbar.back.setOnClickListener { viewModel.backClicked() }
        binding.next.setOnClickListener { viewModel.nextClicked(phoneNumber,binding.code.text,isNetworkAvailable()) }

        (binding.code[5] as EditText).setOnEditorActionListener { _, actionId, event ->
            if (event != null && event.keyCode == KeyEvent.KEYCODE_ENTER || actionId == EditorInfo.IME_ACTION_DONE) {
                viewModel.nextClicked(phoneNumber,binding.code.text,isNetworkAvailable())
            }
            false
        }

        internetChangeListener().observe(this,internetObserver)
        viewModel.back.observe(this, back)
        viewModel.navToUserReg.observe(this, observerNavigate)
        viewModel.navToDashboard.observe(this, navToDashboard)
        viewModel.error.observe(this,errorObserver)
        viewModel.progressbar.observe(this,progressObserver)
    }
    private val internetObserver = Observer<Boolean>{
        viewModel.onCreated(phoneNumber,builder,it)

        binding.toolbar.title.text = getString(
            if (it) R.string.Authentication
            else R.string.connection
        )
    }

    private val navToDashboard = Observer<Unit>{
        findNavController().navigate(AuthScreenDirections.actionAuthScreen2ToContainerScreen())
    }

    private val progressObserver = Observer<Boolean> {
        binding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
    }

    private val observerNavigate = Observer<Unit>{
        findNavController().navigate(R.id.action_authScreen2_to_registrationUserScreen2, bundleOf("numberPhone" to phoneNumber))
    }

   private val back = Observer<Unit> {
        requireActivity().onBackPressed()
    }

    private val errorObserver = Observer<String>{
        Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
    }
}