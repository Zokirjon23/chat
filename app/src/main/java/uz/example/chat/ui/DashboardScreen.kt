package uz.example.chat.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.example.chat.R
import uz.example.chat.databinding.FragmentDashboardScreenBinding
import uz.example.chat.viewModel.impl.DashboardViewModelImpl
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardScreen : Fragment(R.layout.fragment_dashboard_screen) {

    private val binding: FragmentDashboardScreenBinding by viewBinding()
    private val viewModel: DashboardViewModelImpl by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.opener.setOnClickListener {
            (requireParentFragment().parentFragment as ContainerScreen).openCloseDrawer()
        }

        binding.fab.setOnClickListener {
            findNavController()
                .navigate(R.id.action_dashboardScreen2_to_contactsScreen)
        }

    }
}