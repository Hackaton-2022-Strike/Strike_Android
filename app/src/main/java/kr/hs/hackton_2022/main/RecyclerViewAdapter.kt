package kr.hs.hackton_2022.main

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.hs.hackton_2022.ErrandData
import kr.hs.hackton_2022.data.RecycleData
import kr.hs.hackton_2022.databinding.ItemListBinding

class RecyclerViewAdapter(val context: Context) :
    RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {
    var dataList = mutableListOf<RecycleData>()

    inner class MyViewHolder(private val binding: ItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: RecycleData) {
            binding.tvTitle.text = data.tv_name
            binding.tvMain.text = data.tv_main
            binding.layout.setOnClickListener {
                Intent(context, ErrandData::class.java).apply {
                    putExtra("data", data)
                }.run { context.startActivity(this) }
            };
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(dataList[position])
    }
}