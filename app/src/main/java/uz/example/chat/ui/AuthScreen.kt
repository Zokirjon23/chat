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
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import java.util.concurrent.TimeUnit
import android.util.Log
import androidx.core.view.get
import androidx.lifecycle.Observer
import uz.example.chat.util.isNetworkAvailable
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthScreen : Fragment(R.layout.fragment_auth_screen) {

    private lateinit var binding: FragmentAuthScreenBinding
    private lateinit var phoneNumber: String
    private lateinit var auth: FirebaseAuth
    private var sentCode: String = ""

    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentAuthScreenBinding.bind(view)
        binding.toolbar.title.setText(R.string.Authentication)
        val viewModel: AuthScreenViewModelImpl by viewModels()
        phoneNumber = arguments?.getString("number")!!
        startAuth(phoneNumber)



        viewModel.back.observe(this, back)
        binding.toolbar.back.setOnClickListener { viewModel.backClicked() }

        viewModel.next.observe(this, next)
        binding.next.setOnClickListener { viewModel.nextClicked() }

        (binding.code[5] as EditText).setOnEditorActionListener { _, actionId, event ->
            if (event != null && event.keyCode == KeyEvent.KEYCODE_ENTER || actionId == EditorInfo.IME_ACTION_DONE) {
                viewModel.nextClicked()
            }
            false
        }
    }

   private val next = Observer<Unit> {
        action()
    }

   private val back = Observer<Unit> {
        requireActivity().onBackPressed()
    }

    private fun startAuth(phone: String) {
        auth = FirebaseAuth.getInstance()
        auth.setLanguageCode("uz")

        if (isNetworkAvailable()) {
            val options = PhoneAuthOptions.newBuilder(auth)
                .setPhoneNumber(phone)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(requireActivity())
                .setCallbacks(object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                    override fun onCodeSent(
                        p0: String,
                        p1: PhoneAuthProvider.ForceResendingToken
                    ) {
                        sentCode = p0
                    }

                    override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                        binding.progressBar.visibility = View.VISIBLE
                        signInWithPhoneAuthCredential(p0)
                    }

                    override fun onVerificationFailed(p0: FirebaseException) {
                        Log.d("EEE",p0.message!!)
                    }
                })
                .build()
            PhoneAuthProvider.verifyPhoneNumber(options)
        } else {
            Toast.makeText(requireContext(), "Check connection", Toast.LENGTH_SHORT).show()
        }

    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential).addOnCompleteListener(requireActivity()) { p0 ->
            if (p0.isSuccessful) {
                binding.code.text = credential.smsCode
                binding.progressBar.visibility = View.INVISIBLE
                findNavController()
                    .navigate(
                        R.id.action_authScreen2_to_registrationUserScreen2,
                        bundleOf("number" to phoneNumber)
                    )
            } else {
                Toast.makeText(requireContext(), "Error!", Toast.LENGTH_SHORT).show()
                binding.progressBar.visibility = View.INVISIBLE
            }
        }
    }

    private fun action() {
        if (binding.code.text.isEmpty() || binding.code.text.length != 6) {
            Toast.makeText(requireContext(), "Enter code", Toast.LENGTH_SHORT).show()
        } else if (isNetworkAvailable()) {
            binding.progressBar.visibility = View.VISIBLE
            try {
                val phoneAuthCredential =
                    PhoneAuthProvider.getCredential(sentCode, binding.code.text.toString())
                signInWithPhoneAuthCredential(phoneAuthCredential)
            }catch (e : Exception){
                Toast.makeText(requireContext(), e.message, Toast.LENGTH_SHORT).show()
                Log.d("CCC",e.message!!)
            }
        } else {
            Toast.makeText(requireContext(), "connect to network", Toast.LENGTH_SHORT).show()
        }
    }
}