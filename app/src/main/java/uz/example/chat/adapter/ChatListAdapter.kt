package uz.example.chat.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.example.chat.model.MyMassage
import uz.example.chat.R
import uz.example.chat.databinding.ReceiverItemChatBinding
import uz.example.chat.databinding.SenderItemChatBinding

class ChatListAdapter(private var messages: ArrayList<MyMassage>) :
    RecyclerView.Adapter<uz.example.chat.adapter.ChatListAdapter.MyHolder>() {
    private val RECEIVE_TYPE = 1

    override fun getItemViewType(position: Int): Int {
        return messages[position].type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatListAdapter.MyHolder {
        val inflater = LayoutInflater.from(parent.context)
        if (viewType == RECEIVE_TYPE) {
            return MyHolder(inflater.inflate(R.layout.receiver_item_chat, parent, false), viewType)
        }
        return MyHolder(inflater.inflate(R.layout.sender_item_chat, parent, false), viewType)
    }

    override fun onBindViewHolder(holder: ChatListAdapter.MyHolder, position: Int) {
        holder.bind(messages[position])
    }

    override fun getItemCount() = messages.size

   inner class MyHolder(private var view: View, private var viewType: Int) :
        RecyclerView.ViewHolder(view) {

        fun bind(data: MyMassage) {
            if (viewType == RECEIVE_TYPE) {
                val binding = SenderItemChatBinding.bind(view)
                binding.textMessage.text = data.text
            } else {
                val binding = ReceiverItemChatBinding.bind(view)
                binding.textMessage.text = data.text
            }
        }
    }

    fun addMessage(message: MyMassage) {
        messages.add(message)
        notifyItemInserted(messages.size)
    }
}