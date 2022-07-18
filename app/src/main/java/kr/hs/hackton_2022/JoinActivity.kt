package kr.hs.hackton_2022

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import kr.hs.hackton_2022.data.JoinData
import kr.hs.hackton_2022.databinding.ActivityJoinBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.regex.Pattern

class JoinActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding : ActivityJoinBinding
    val PwToggle: ImageView by lazy {
        findViewById(R.id.pw_toggle)
    }
    val IdEt: EditText by lazy {
        findViewById(R.id.et_id)
    }
    val PwEt: EditText by lazy {
        findViewById(R.id.et_pw)
    }
    val RPwEt: EditText by lazy {
        findViewById(R.id.et_pwCheck)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJoinBinding.inflate(layoutInflater)
        setContentView(binding.root)

        settingListener()
    }
    fun settingListener(){
        binding.joinBtn.setOnClickListener(this)
        PwToggle.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v){
            PwToggle -> {
                if (PwToggle.tag.equals("0")) {
                    PwToggle.tag = "1"
                    PwToggle.setImageResource(R.drawable.view)

                    binding.etPw.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                    binding.etPwCheck.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                } else {
                    PwToggle.tag = "0"
                    PwToggle.setImageResource(R.drawable.hidden)

                    binding.etPw.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                    binding.etPwCheck.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD

                }
                binding.etPw.setSelection(binding.etPw.text.length)
            }
            binding.joinBtn -> {
                joinCheck()
            }
        }
    }
    fun joinCheck(){
        if (!Pattern.matches("^(?=.*[A-Za-z]).{5,11}.\$", IdEt.text.toString())) {
            IdEt.requestFocus()
            Toast.makeText(this, "아이디는 6~12자 사이여야합니다.", Toast.LENGTH_SHORT).show()
        }else if (!Pattern.matches("^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[\$@\$!%*#?&]).{7,14}.\$", PwEt.text.toString())) {
            PwEt.requestFocus()
            Toast.makeText(this, "비밀번호는 8~15자 문자와 숫자, 특수문자가 필수로 포함되어야합니다.", Toast.LENGTH_SHORT).show()
        }else if(PwEt.text.toString() != RPwEt.text.toString()){
            RPwEt.requestFocus()
            Toast.makeText(this, "비밀번호가 일치하지않습니다.", Toast.LENGTH_SHORT).show()
        }else if(binding.etName.text.isNullOrBlank()){
            binding.etName.requestFocus()
            Toast.makeText(this, "이름을 입력해주세요.", Toast.LENGTH_SHORT).show()
        }
        else {
            val data = JoinData(binding.etName.text.toString(), IdEt.text.toString(), PwEt.text.toString())
            RetrofitBuilder.api.HackathonJoin(data).enqueue(object :
                Callback<JoinData> {
                override fun onResponse(call: Call<JoinData>, response: Response<JoinData>) {
                    val data = response.body().toString()
                    Log.d("TAG", data)

                    val intent = Intent(applicationContext, LoginActivity::class.java)
                    Toast.makeText(applicationContext, "회원가입 성공!", Toast.LENGTH_SHORT).show()
                    finish()
                    startActivity(intent)
                }

                override fun onFailure(call: Call<JoinData>, t: Throwable) {
                    Log.d("Tag", t.toString())
                    Toast.makeText(applicationContext, "회원가입 실패", Toast.LENGTH_SHORT).show()
                }
                })
        }
    }
}