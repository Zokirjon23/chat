package uz.example.chat.ui

import android.Manifest
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.example.chat.R
import uz.example.chat.adapter.ContactListAdapter
import uz.example.chat.databinding.UserContactsScreenFragmentBinding
import uz.example.chat.model.User
import uz.example.chat.util.isNetworkAvailable
import uz.example.chat.viewModel.impl.ContactViewModelImpl
import com.permissionx.guolindev.PermissionX
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContactsScreen : Fragment(R.layout.user_contacts_screen_fragment) {

    private val binding: UserContactsScreenFragmentBinding by viewBinding()
    private lateinit var adapter: ContactListAdapter
    private val viewModel: ContactViewModelImpl by viewModels()

    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.toolbar.menuIcon.visibility = View.VISIBLE
        binding.toolbar.title.setText(R.string.contacts)

        binding.toolbar.back.setOnClickListener { viewModel.onBackClicked() }

        viewModel.permission.observe(this, permissionObserver)
        viewModel.contacts.observe(this, contactObserver)
        viewModel.back.observe(this, back)
    }

    private val permissionObserver = Observer<Unit> {
        PermissionX.init(this)
            .permissions(
                Manifest.permission.READ_CONTACTS
            )
            .request { allGranted, _, _ ->
                Log.d("AAA",allGranted.toString())

                if (allGranted) {
                    viewModel.onCreatedView(requireActivity().contentResolver, isNetworkAvailable())
                } else {
//                    requireActivity().onBackPressed()
                }
            }
    }

    private val back = Observer<Unit> {
        requireActivity().onBackPressed()
    }

    private val contactObserver = Observer<ArrayList<User>> {
        Toast.makeText(requireContext(), it.size.toString(), Toast.LENGTH_SHORT).show()
        adapter = ContactListAdapter(it)
        binding.listView.adapter = adapter
        adapter.setItemOnClicked { user ->
            findNavController().navigate(
                R.id.action_contactsScreen_to_chatScreen2,
                bundleOf("chat" to user)
            )
        }
    }
}