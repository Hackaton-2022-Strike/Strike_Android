package kr.hs.hackton_2022

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kr.hs.hackton_2022.data.ErRecycleData
import kr.hs.hackton_2022.data.ErrecycleDataItem
import kr.hs.hackton_2022.databinding.ActivityMyerrandBinding
import kr.hs.hackton_2022.main.ErRecyclerViewAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyerrandActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMyerrandBinding
    private var dataEr : ErRecycleData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyerrandBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getPosts()
        initDataRecyclerView()

        binding.BackArrow.setOnClickListener {
            finish()
        }
    }

    fun initDataRecyclerView() {
        val adapter = ErRecyclerViewAdapter(this)
        adapter.dataListEr = dataEr
        binding.errandRecycle.adapter = adapter
        binding.errandRecycle.layoutManager = LinearLayoutManager(this)
    }
    private fun getPosts() {
        //val data = LoginData(binding.etId.text.toString(), binding.etPw.text.toString())
        RetrofitBuilder.api.getErposts().enqueue(object :
            Callback<ErRecycleData> {
            override fun onResponse(call: Call<ErRecycleData>, response: Response<ErRecycleData>) {
                if (response.isSuccessful) {
                    dataEr = response.body()
                    Log.d("post", dataEr.toString())
                    initDataRecyclerView()

//                    Log.d("TAG", data)
//
//                    val intent = Intent(applicationContext, MainActivity::class.java)
//                    Toast.makeText(applicationContext, "로그인 성공!", Toast.LENGTH_SHORT).show()
//                    finish()
//                    startActivity(intent)

                }
            }

            override fun onFailure(call: Call<ErRecycleData>, t: Throwable) {
                Log.d("Tag", t.toString())
                Toast.makeText(applicationContext, "목록조회에 실패하셨습니다.", Toast.LENGTH_SHORT).show()
            }
        })
    }
}