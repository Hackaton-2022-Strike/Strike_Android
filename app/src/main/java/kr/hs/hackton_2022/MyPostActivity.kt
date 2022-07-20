package kr.hs.hackton_2022

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kr.hs.hackton_2022.data.ErRecycleData
import kr.hs.hackton_2022.data.infoRecycleData
import kr.hs.hackton_2022.databinding.ActivityMyPostBinding
import kr.hs.hackton_2022.main.infoRecyclerViewAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyPostActivity : AppCompatActivity() {
    private var appDatabase: AppDatabase? = null
    private lateinit var binding : ActivityMyPostBinding
    private var datainfo : infoRecycleData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyPostBinding.inflate(layoutInflater)
        setContentView(binding.root)
        appDatabase = AppDatabase.getInstance(this)
        getPosts()
        initDataRecyclerView()

        binding.BackArrow.setOnClickListener {
            finish()
        }
    }

    fun initDataRecyclerView() {
        val adapter = infoRecyclerViewAdapter(this)
        adapter.dataListinfo = datainfo
        binding.myPostRecycle.adapter = adapter
        binding.myPostRecycle.layoutManager = LinearLayoutManager(this)
    }
    private fun getPosts() {
        //val data = LoginData(binding.etId.text.toString(), binding.etPw.text.toString())
        RetrofitBuilder.api.getmyinfo(appDatabase!!.dao().getAll().mb_id).enqueue(object :
            Callback<infoRecycleData> {
            override fun onResponse(call: Call<infoRecycleData>, response: Response<infoRecycleData>) {
                if (response.isSuccessful) {
                    datainfo = response.body()
                    Log.d("post", datainfo.toString())
                    initDataRecyclerView()

//                    Log.d("TAG", data)
//
//                    val intent = Intent(applicationContext, MainActivity::class.java)
//                    Toast.makeText(applicationContext, "로그인 성공!", Toast.LENGTH_SHORT).show()
//                    finish()
//                    startActivity(intent)

                }
            }

            override fun onFailure(call: Call<infoRecycleData>, t: Throwable) {
                Log.d("Tag", t.toString())
                Toast.makeText(applicationContext, "목록조회에 실패하셨습니다.", Toast.LENGTH_SHORT).show()
            }
        })
    }
}