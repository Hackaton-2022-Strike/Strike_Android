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
import kr.hs.hackton_2022.*
import kr.hs.hackton_2022.data.LoginData
import kr.hs.hackton_2022.data.RecycleData
import kr.hs.hackton_2022.databinding.FragmentMainBinding
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private var data : RecycleData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    private fun initDataRecyclerView(){
        val adapter = RecyclerViewAdapter(requireContext())
        Log.d("postasd", data.toString())
        adapter.dataList = data
        binding.recycler.adapter = adapter
        Log.d("postasd", adapter.dataList.toString())

        binding.recycler.layoutManager = LinearLayoutManager(requireContext())
    }
    private fun initFabListener(){
        binding.fab.setOnClickListener {
            val intent = Intent(requireContext(), RegisterActivity::class.java)
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
            Callback<RecycleData> {
            override fun onResponse(call: Call<RecycleData>, response: Response<RecycleData>) {
                if (response.isSuccessful) {
                    data = response.body()
                    Log.d("post", data.toString())
                    initDataRecyclerView()

//                    Log.d("TAG", data)
//
//                    val intent = Intent(applicationContext, MainActivity::class.java)
//                    Toast.makeText(applicationContext, "로그인 성공!", Toast.LENGTH_SHORT).show()
//                    finish()
//                    startActivity(intent)

                }
            }

            override fun onFailure(call: Call<RecycleData>, t: Throwable) {
                Log.d("Tag", t.toString())
                Toast.makeText(requireContext(), "목록조회에 실패하셨습니다.", Toast.LENGTH_SHORT).show()
            }
        })
    }
}