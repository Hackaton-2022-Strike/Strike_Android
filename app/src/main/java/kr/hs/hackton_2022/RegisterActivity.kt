package kr.hs.hackton_2022

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import kr.hs.hackton_2022.data.JoinData
import kr.hs.hackton_2022.data.PostData
import kr.hs.hackton_2022.databinding.ActivityRegisterBinding
import kr.hs.hackton_2022.main.MainFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private var appDatabase: AppDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val type = intent.getStringExtra("type")

        appDatabase = AppDatabase.getInstance(this)

        binding.BackArrow.setOnClickListener {
            finish()
        }

        binding.saveBtn.setOnClickListener {

            if(type.equals("Er")){
                Erpost()
            } else {
                infopost()
            }

            finish()
        }


    }

    fun Erpost(){
        val data = PostData(appDatabase!!.dao().getAll().mb_name, binding.etTitle.text.toString(), binding.etMain.text.toString(), appDatabase!!.dao().getAll().mb_id)
        RetrofitBuilder.api.Erpost(data).enqueue(object :
            Callback<PostData> {
            override fun onResponse(call: Call<PostData>, response: Response<PostData>) {
                Toast.makeText(applicationContext, "글쓰기 성공", Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<PostData>, t: Throwable) {
                Log.d("Tag", t.toString())
                Toast.makeText(applicationContext, "글쓰기 실패", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun infopost(){
        val data = PostData(appDatabase!!.dao().getAll().mb_name, binding.etTitle.text.toString(), binding.etMain.text.toString(), appDatabase!!.dao().getAll().mb_id)
        RetrofitBuilder.api.infopost(data).enqueue(object :
            Callback<PostData> {
            override fun onResponse(call: Call<PostData>, response: Response<PostData>) {
                Toast.makeText(applicationContext, "글쓰기 성공", Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<PostData>, t: Throwable) {
                Log.d("Tag", t.toString())
                Toast.makeText(applicationContext, "글쓰기 실패", Toast.LENGTH_SHORT).show()
            }
        })
    }
}