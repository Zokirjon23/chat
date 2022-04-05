package uz.example.chat.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.example.chat.R
import uz.example.chat.databinding.FragmentContainerScreenBinding
import uz.example.chat.databinding.NavHeaderMainBinding
import uz.example.chat.model.User
import uz.example.chat.viewModel.impl.ContainerViewModelImpl

@AndroidEntryPoint
class ContainerScreen : Fragment(R.layout.fragment_container_screen) {

    private val binding: FragmentContainerScreenBinding by viewBinding()
    private val viewModel: ContainerViewModelImpl by viewModels()
    private lateinit var navController : NavController

    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        navController = requireActivity().findNavController(R.id.nav_host_fragment)
        binding.navOI.setupWithNavController(navController)

        binding.navOI.setNavigationItemSelectedListener {dest ->
            when(dest.itemId) {
                R.id.logOut -> viewModel.logOutClicked()
                else -> {
                    NavigationUI.onNavDestinationSelected(dest, navController)
                    binding.drawerLayout.closeDrawers()
                }
            }
            true
        }

        viewModel.userLiveData.observe(this,userObserver)
        viewModel.navToRegistration.observe(this,logOutObserver)
    }
    private val logOutObserver = Observer<Unit> {
        findNavController().navigate(R.id.action_containerScreen_to_registrationScreen2)
    }

    private val userObserver = Observer<User> {
        val headerBinding: NavHeaderMainBinding =
            NavHeaderMainBinding.bind(binding.navOI.getHeaderView(0))
        headerBinding.number.text = it.number
        headerBinding.userName.text = it.name
    }

    fun openCloseDrawer() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else
            binding.drawerLayout.openDrawer(GravityCompat.START)
    }
}