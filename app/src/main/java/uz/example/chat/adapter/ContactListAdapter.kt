package uz.example.chat.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.example.chat.R
import uz.example.chat.databinding.ContactListItemBinding
import uz.example.chat.model.User

class ContactListAdapter(private var contacts: ArrayList<User>) :
    RecyclerView.Adapter<ContactListAdapter.MyHolder>() {
    private var itemClicked: ((User) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding =
            ContactListItemBinding.bind(inflater.inflate(R.layout.contact_list_item, parent, false))
        return MyHolder(binding)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.bind(contacts[position])
    }

    override fun getItemCount() = contacts.size

    inner class MyHolder(private val binding: ContactListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: User) {
            binding.layout.setOnClickListener {
                itemClicked?.invoke(data)
            }
            binding.contactName.text = data.name
            binding.contactLastSeen.text = data.number
        }
    }

    fun setItemOnClicked(f: (User) -> Unit) {
        itemClicked = f
    }
}