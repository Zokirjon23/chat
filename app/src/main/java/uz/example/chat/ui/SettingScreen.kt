package uz.example.chat.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import uz.example.chat.R
import uz.example.chat.databinding.FragmentSettingScreenBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingScreen : Fragment(R.layout.fragment_setting_screen) {

    lateinit var binding: FragmentSettingScreenBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentSettingScreenBinding.bind(view)
        binding.toolbar.title.setText(R.string.setting)
        binding.toolbar.menuIcon.setBackgroundResource(R.drawable.ic_baseline_check_24)
        binding.toolbar.menuIcon.visibility = View.VISIBLE

    }
}