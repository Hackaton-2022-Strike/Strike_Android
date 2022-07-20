package kr.hs.hackton_2022.main

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import kr.hs.hackton_2022.*
import kr.hs.hackton_2022.data.JoinData
import kr.hs.hackton_2022.data.LoginData
import kr.hs.hackton_2022.databinding.FragmentMyDataBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MyDataFragment : Fragment() {
    private lateinit var binding: FragmentMyDataBinding
    private lateinit var userinfo : UserEntity
    private var appDatabase: AppDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyDataBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        appDatabase = AppDatabase.getInstance(requireContext())
        userinfo  = appDatabase!!.dao().getAll()
        binding.etName.setText(userinfo.mb_name)

        binding.writeLayout.setOnClickListener {
            val intent = Intent(requireContext(), MyPostActivity::class.java)
            startActivity(intent)
        }
        binding.errandLayout.setOnClickListener {
            val intent = Intent(requireContext(), MyerrandActivity::class.java)
            startActivity(intent)
        }
        binding.changeImg.setOnClickListener {
            if(binding.changeImg.tag.equals("0")){
                binding.changeImg.tag = 1
                binding.etName.isEnabled = true
                binding.etName.setSelection(binding.etName.length())
                val imm = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
                binding.etName.requestFocus()

            }else {
                appDatabase!!.dao().update(binding.etName.text.toString(), userinfo.mb_name)
                userinfo = appDatabase!!.dao().getAll()
                UpdateUser()
                binding.changeImg.tag = 0
                binding.etName.isEnabled = false

            }
        }
        binding.logoutLayout.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("로그아웃 확인")
                .setMessage("정말로 로그아웃하시겠습니까?")
                .setPositiveButton("로그아웃", DialogInterface.OnClickListener {_, _ ->
                    appDatabase!!.dao().delete()
                    requireActivity().finish()
                    val intent = Intent(requireContext(), LoginActivity::class.java)
                    startActivity(intent)
                })
                .setNegativeButton("취소", DialogInterface.OnClickListener{_, _ ->

                }).show()

        }
    }

    private fun UpdateUser() {
        RetrofitBuilder.api.updatemy(userinfo).enqueue(object :
            Callback<UserEntity> {
            override fun onResponse(call: Call<UserEntity>, response: Response<UserEntity>) {
                Toast.makeText(requireContext(), "정보 변경 완료", Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<UserEntity>, t: Throwable) {
                Log.d("Tag", t.toString())
                Toast.makeText(requireContext(), "정보 변경 실패", Toast.LENGTH_SHORT).show()
            }
        })
    }
}