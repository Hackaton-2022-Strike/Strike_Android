package kr.hs.hackton_2022

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kr.hs.hackton_2022.data.ErrecycleDataItem
import kr.hs.hackton_2022.databinding.ActivityMyerrandBinding
import kr.hs.hackton_2022.main.ErRecyclerViewAdapter

class MyerrandActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMyerrandBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyerrandBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initDataRecyclerView()
    }

    fun initDataRecyclerView() {
        val adapter = ErRecyclerViewAdapter(this)
        binding.errandRecycle.adapter = adapter
        binding.errandRecycle.layoutManager = LinearLayoutManager(this)
    }
}