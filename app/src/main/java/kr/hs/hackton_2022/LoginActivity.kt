package kr.hs.hackton_2022

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import kr.hs.hackton_2022.data.LoginData
import kr.hs.hackton_2022.databinding.ActivityLoginBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityLoginBinding
    private var appDatabase: AppDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        settingListener()
        appDatabase = AppDatabase.getInstance(this)
        isLogin()


    }

    private fun settingListener() {
        binding.loginBtn.setOnClickListener(this)
        binding.joinBtn.setOnClickListener(this)
    }

    private fun isLogin() {
            val Login: UserEntity? = appDatabase!!.dao().getAll()
            //Log.d("TAG1357", Login.mb_id)
            if (Login != null) {
                val intent = Intent(applicationContext, MainActivity::class.java)
                finish()
                startActivity(intent)

            }
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.loginBtn -> {
                CheckLogin()
            }
            binding.joinBtn -> {
                Log.d("join", "hello")
                val intent = Intent(this, JoinActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun CheckLogin() {
        val data = LoginData(binding.etId.text.toString(), binding.etPw.text.toString())
        RetrofitBuilder.api.Hackathonlogin(data).enqueue(object :
            Callback<UserEntity> {
            override fun onResponse(call: Call<UserEntity>, response: Response<UserEntity>) {
                if (response.isSuccessful) {
                    var data: UserEntity? = response.body()

                    val intent = Intent(applicationContext, MainActivity::class.java)
                    Toast.makeText(applicationContext, "로그인 성공!", Toast.LENGTH_SHORT).show()
                    finish()
                    startActivity(intent)
                    InsertData(data!!)

                }
            }

            override fun onFailure(call: Call<UserEntity>, t: Throwable) {
                Log.d("Tag", t.toString())
                Toast.makeText(applicationContext, "로그인에 실패하셨습니다.", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun InsertData(data: UserEntity) {


        var res = appDatabase?.dao()?.insert(data)
        Log.d("TAG1234", res.toString())

        Log.d("TAG1234", "datas : " + appDatabase?.dao()?.getAll())
    }
}
