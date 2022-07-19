package kr.hs.hackton_2022.main

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.hs.hackton_2022.DetailPost
import kr.hs.hackton_2022.data.infoRecycleData
import kr.hs.hackton_2022.data.infoRecycleDataItem
import kr.hs.hackton_2022.databinding.ItemListBinding

class infoRecyclerViewAdapter(val context: Context) :
    RecyclerView.Adapter<infoRecyclerViewAdapter.MyViewHolder>() {
    var dataListinfo : infoRecycleData? = null

    inner class MyViewHolder(private val binding: ItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: infoRecycleDataItem) {
            binding.tvTitle.text = data.info_title
            binding.tvMain.text = data.info_contents
            binding.layout.setOnClickListener {
                Intent(context, DetailPost::class.java).apply {
                    putExtra("data", data)
                    putExtra("type", "info")
                }.run { context.startActivity(this) }
            };
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataListinfo?.size ?: 0
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(dataListinfo!![position])
    }
}