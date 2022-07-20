package kr.hs.hackton_2022.main

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.hs.hackton_2022.DetailPost
import kr.hs.hackton_2022.data.SchoolDataItem
import kr.hs.hackton_2022.databinding.ItemListBinding

class ShRecyclerViewAdapter(val context: Context) :
    RecyclerView.Adapter<ShRecyclerViewAdapter.MyViewHolder>() {
        var dataListSh : MutableList<SchoolDataItem>? = null

        inner class MyViewHolder(private val binding: ItemListBinding) :
            RecyclerView.ViewHolder(binding.root) {
            fun bind(data: SchoolDataItem) {
                binding.tvTitle.text = data.school_title
                binding.tvMain.text = data.school_name
                binding.layout.setOnClickListener {
                    Intent(context, DetailPost::class.java).apply {
                        putExtra("data", data)
                        putExtra("type", "Sh")
                    }.run { context.startActivity(this) }
                };
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataListSh?.size ?: 0
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(dataListSh!![position])
    }
}
