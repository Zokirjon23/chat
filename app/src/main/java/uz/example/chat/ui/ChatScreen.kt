package uz.example.chat.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import uz.example.chat.model.MyMassage
import uz.example.chat.model.User
import uz.example.chat.R
import uz.example.chat.adapter.ChatListAdapter
import uz.example.chat.databinding.FragmentChatScreenBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChatScreen : Fragment(R.layout.fragment_chat_screen) {

    private lateinit var binding: FragmentChatScreenBinding
    private lateinit var list: ChatListAdapter
    private var messages = ArrayList<MyMassage>()
    private lateinit var receiver: User

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentChatScreenBinding.bind(view)
        receiver = arguments?.getSerializable("chat") as User
        binding.receiverName.text = receiver.name

        list = ChatListAdapter(messages)
        binding.chatList.adapter = list

        binding.sendBtn.setOnClickListener {
            list.addMessage(MyMassage(binding.massageTextview.text.toString(), 0))
            binding.massageTextview.setText("")
        }

        binding.backIcon.setOnClickListener { requireActivity().onBackPressed() }
    }
}