package kr.hs.hackton_2022

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import kr.hs.hackton_2022.data.ErrecycleDataItem
import kr.hs.hackton_2022.data.SchoolDataItem
import kr.hs.hackton_2022.data.infoRecycleDataItem
import kr.hs.hackton_2022.databinding.ActivityDetailPostBinding
import kr.hs.hackton_2022.databinding.ActivityJoinBinding
import kr.hs.hackton_2022.main.ErRecyclerViewAdapter

class DetailPost : AppCompatActivity() {
    private lateinit var binding : ActivityDetailPostBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailPostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val type = intent.getStringExtra("type")
        var index : Int? = null

        when {
            type.equals("Er") -> {
                val data = intent.getSerializableExtra("data") as ErrecycleDataItem
                binding.Contents.text = data.Er_contents
                binding.Title.text = data.Er_title
                binding.writer.text = data.Er_writer
                index = data.Er_id
            }
            type.equals("Sh") -> {
                val data = intent.getSerializableExtra("data") as SchoolDataItem
                binding.Contents.text = data.school_content
                binding.Title.text = data.school_title
                binding.writer.text = data.school_name
            }
            else -> {
                val data = intent.getSerializableExtra("data") as infoRecycleDataItem
                binding.Contents.text = data.info_contents
                binding.Title.text = data.info_title
                binding.writer.text = data.info_writer
                index = data.info_id
            }
        }

        binding.BackArrow.setOnClickListener{
            finish()
        }



    }
}