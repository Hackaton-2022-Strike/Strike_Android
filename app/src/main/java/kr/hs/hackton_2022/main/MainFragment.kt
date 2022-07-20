package kr.hs.hackton_2022.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import kr.hs.hackton_2022.*
import kr.hs.hackton_2022.data.ErRecycleData
import kr.hs.hackton_2022.databinding.FragmentMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private var dataEr : ErRecycleData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    private fun initDataRecyclerView(){
        val adapter = ErRecyclerViewAdapter(requireContext())
        Log.d("postasd", dataEr.toString())
        adapter.dataListEr = dataEr
        binding.recycler.adapter = adapter
        Log.d("postasd", adapter.dataListEr.toString())

        binding.recycler.layoutManager = LinearLayoutManager(requireContext())
    }
    private fun initFabListener(){
        binding.fab.setOnClickListener {
            val intent = Intent(requireContext(), RegisterActivity::class.java)
            intent.putExtra("type", "Er")
            startActivity(intent)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getPosts()
        initFabListener()
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
                Toast.makeText(requireContext(), "목록조회에 실패하셨습니다.", Toast.LENGTH_SHORT).show()
            }
        })
    }

}