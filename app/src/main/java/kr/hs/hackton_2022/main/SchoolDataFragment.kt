package kr.hs.hackton_2022.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kr.hs.hackton_2022.data.SchoolDataItem
import kr.hs.hackton_2022.databinding.FragmentSchoolDataBinding


class SchoolDataFragment : Fragment() {
    private lateinit var binding : FragmentSchoolDataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private fun initDataRecyclerView(){
        var list = mutableListOf(
            SchoolDataItem("title", "name", "contents"),
            SchoolDataItem("title", "name", "contents"),
            SchoolDataItem("title", "name", "contents"),
            SchoolDataItem("title", "name", "contents")
        )
        val adapter = ShRecyclerViewAdapter(requireContext())
        adapter.dataListSh = list
        binding.recycler.adapter = adapter
        binding.recycler.layoutManager = LinearLayoutManager(requireContext())
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSchoolDataBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initDataRecyclerView()
    }

}