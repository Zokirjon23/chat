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
import dagger.hilt.android.AndroidEntryPoint
import uz.example.chat.R
import uz.example.chat.databinding.FragmentRegistrationUserScreenBinding
import uz.example.chat.model.User
import uz.example.chat.viewModel.impl.UserRegViewModelImpl

@AndroidEntryPoint
class RegistrationUserScreen : Fragment(R.layout.fragment_registration_user_screen) {

    private val binding: FragmentRegistrationUserScreenBinding by viewBinding()
    private val viewModel: UserRegViewModelImpl by viewModels()
    private lateinit var user: User

    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.toolbar.title.setText(R.string.User_registration)
        val phoneNumber = requireArguments().getString("numberPhone")

        binding.save.setOnClickListener {
            user = User(0, phoneNumber!!,
                binding.name.text.toString(),
                binding.lastname.text.toString(),
            )
            viewModel.saveOnClicked(user)
        }
        binding.toolbar.back.setOnClickListener { viewModel.onBackClicked() }
        viewModel.successSent.observe(this, successSent)
        viewModel.errorMessage.observe(this, errorSent)
        viewModel.back.observe(this, back)
        viewModel.progressbar.observe(this,progressObserver)
    }

    private val progressObserver = Observer<Boolean>{
        binding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
    }

    private val successSent = Observer<Unit> {
        binding.progressBar.visibility = View.INVISIBLE
        findNavController()
            .navigate(
                R.id.action_registrationUserScreen2_to_containerScreen
            )
    }
    private val errorSent = Observer<String> {
        Toast.makeText(requireActivity(), it, Toast.LENGTH_SHORT).show()
    }
    private val back = Observer<Unit> {
        requireActivity().onBackPressed()
    }
}