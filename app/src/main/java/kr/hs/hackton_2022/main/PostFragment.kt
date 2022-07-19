package kr.hs.hackton_2022.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kr.hs.hackton_2022.RegisterActivity
import kr.hs.hackton_2022.RetrofitBuilder
import kr.hs.hackton_2022.data.ErRecycleData
import kr.hs.hackton_2022.data.infoRecycleData
import kr.hs.hackton_2022.databinding.FragmentPostBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PostFragment : Fragment() {
    private lateinit var binding: FragmentPostBinding
    private var datainfo : infoRecycleData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }
    private fun initDataRecyclerView(){
        val adapter = infoRecyclerViewAdapter(requireContext())
        adapter.dataListinfo = datainfo
        binding.recycler.adapter = adapter
        binding.recycler.layoutManager = LinearLayoutManager(requireContext())
    }

    fun initFabListener(){
        binding.fab.setOnClickListener {
            val intent = Intent(requireContext(), RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPostBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getPosts()
        initFabListener()
    }

    private fun getPosts() {
        //val data = LoginData(binding.etId.text.toString(), binding.etPw.text.toString())
        RetrofitBuilder.api.getinfoposts().enqueue(object :
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
                Toast.makeText(requireContext(), "목록조회에 실패하셨습니다.", Toast.LENGTH_SHORT).show()
            }
        })
    }
}