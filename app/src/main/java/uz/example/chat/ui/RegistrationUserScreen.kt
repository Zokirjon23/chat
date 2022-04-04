package uz.example.chat.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.example.chat.R
import uz.example.chat.databinding.FragmentRegistrationUserScreenBinding
import uz.example.chat.model.User
import uz.example.chat.viewModel.impl.UserRegViewModelImpl
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegistrationUserScreen : Fragment(R.layout.fragment_registration_user_screen) {

    private val binding: FragmentRegistrationUserScreenBinding by viewBinding()
    private val viewModel: UserRegViewModelImpl by viewModels()


    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.toolbar.title.setText(R.string.User_registration)
        val phoneNumber = arguments?.getString("number")

        binding.save.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            viewModel.saveOnClicked(
                User(
                    0,
                    phoneNumber!!,
                    binding.name.text.toString(),
                    binding.lastname.text.toString(),
                )
            )
        }
        binding.toolbar.back.setOnClickListener { viewModel.onBackClicked() }

        viewModel.successSent.observe(this, successSent)
        viewModel.errorMessage.observe(this, errorSent)
        viewModel.back.observe(this, back)

    }


    private val successSent = Observer<Unit> {
        binding.progressBar.visibility = View.INVISIBLE
        findNavController()
            .navigate(R.id.action_registrationUserScreen2_to_containerScreen)
    }
    private val errorSent = Observer<String> {
        binding.progressBar.visibility = View.INVISIBLE
        Toast.makeText(requireActivity(), it, Toast.LENGTH_SHORT).show()
    }
    private val back = Observer<Unit> {
        requireActivity().onBackPressed()
    }
}