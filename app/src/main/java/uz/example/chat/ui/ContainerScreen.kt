package uz.example.chat.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.view.GravityCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.example.chat.model.User
import uz.example.chat.R
import uz.example.chat.databinding.FragmentContainerScreenBinding
import uz.example.chat.databinding.NavHeaderMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContainerScreen : Fragment(R.layout.fragment_container_screen) {

    private val binding : FragmentContainerScreenBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.navOI.setupWithNavController(requireActivity().findNavController(R.id.nav_host_fragment))
    }

    fun openCloseDrawer(){
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)){
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        }else
            binding.drawerLayout.openDrawer(GravityCompat.START)
    }

    fun setHeaderData(user : User){
        val headerBinding: NavHeaderMainBinding = NavHeaderMainBinding.bind(binding.navOI.getHeaderView(0))
        headerBinding.number.text = user.number
        headerBinding.userName.text = user.name
    }
}