package uz.example.chat.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.example.chat.model.User
import uz.example.chat.R
import uz.example.chat.databinding.DashboardItemListBinding

class DashboardAdapter(private val list: ArrayList<User>) :
    RecyclerView.Adapter<DashboardAdapter.MyHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding =
            DashboardItemListBinding.bind(
                inflater.inflate(
                    R.layout.dashboard_item_list,
                    parent,
                    false
                )
            )
        return MyHolder(binding)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

    inner class MyHolder(binding: DashboardItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: User) {

        }
    }

}