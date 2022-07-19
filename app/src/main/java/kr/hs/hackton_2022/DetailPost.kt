package kr.hs.hackton_2022

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import kr.hs.hackton_2022.data.ErrecycleDataItem
import kr.hs.hackton_2022.data.infoRecycleDataItem
import kr.hs.hackton_2022.databinding.ActivityDetailPostBinding
import kr.hs.hackton_2022.databinding.ActivityJoinBinding

class DetailPost : AppCompatActivity() {
    private lateinit var binding : ActivityDetailPostBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailPostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val type = intent.getStringExtra("type")

        if(type.equals("Er")){
            val data = intent.getSerializableExtra("data") as ErrecycleDataItem
            binding.Contents.text = data.Er_contents
        } else {
            val data = intent.getSerializableExtra("data") as infoRecycleDataItem
            binding.Contents.text = data.info_contents
        }



    }
}