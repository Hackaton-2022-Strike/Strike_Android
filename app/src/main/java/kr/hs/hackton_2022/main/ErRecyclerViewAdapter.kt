package kr.hs.hackton_2022.main

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.hs.hackton_2022.PostData
import kr.hs.hackton_2022.data.ErRecycleData
import kr.hs.hackton_2022.data.ErrecycleDataItem
import kr.hs.hackton_2022.databinding.ItemListBinding

class ErRecyclerViewAdapter(val context: Context) :
    RecyclerView.Adapter<ErRecyclerViewAdapter.MyViewHolder>() {
    var dataListEr : ErRecycleData? = null

    inner class MyViewHolder(private val binding: ItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ErrecycleDataItem) {
            binding.tvTitle.text = data.Er_title
            binding.tvMain.text = data.Er_contents
            binding.layout.setOnClickListener {
                Intent(context, PostData::class.java).apply {
                    putExtra("data", data)
                }.run { context.startActivity(this) }
            };
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataListEr?.size ?: 0
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(dataListEr!![position])
    }
}