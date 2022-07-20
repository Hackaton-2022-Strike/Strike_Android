package kr.hs.hackton_2022

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kr.hs.hackton_2022.data.JoinData
import kr.hs.hackton_2022.data.PostData
import kr.hs.hackton_2022.databinding.ActivityRegisterBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.BackArrow.setOnClickListener {
            finish()
        }

        binding.saveBtn.setOnClickListener {



            finish()
        }


    }

//    fun post(){
//        val data = PostData()
//        RetrofitBuilder.api.HackathonJoin(data).enqueue(object :
//            Callback<JoinData> {
//            override fun onResponse(call: Call<JoinData>, response: Response<JoinData>) {
//                val data = response.body().toString()
//                Log.d("TAG", data)
//
//                val intent = Intent(applicationContext, LoginActivity::class.java)
//                Toast.makeText(applicationContext, "회원가입 성공!", Toast.LENGTH_SHORT).show()
//                finish()
//                startActivity(intent)
//            }
//
//            override fun onFailure(call: Call<JoinData>, t: Throwable) {
//                Log.d("Tag", t.toString())
//                Toast.makeText(applicationContext, "회원가입 실패", Toast.LENGTH_SHORT).show()
//            }
//        })
//    }
}