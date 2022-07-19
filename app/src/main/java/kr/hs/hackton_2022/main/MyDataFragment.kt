package kr.hs.hackton_2022.main

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import kr.hs.hackton_2022.LoginActivity
import kr.hs.hackton_2022.MainActivity
import kr.hs.hackton_2022.MyPostActivity
import kr.hs.hackton_2022.MyerrandActivity
import kr.hs.hackton_2022.databinding.FragmentMyDataBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class MyDataFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentMyDataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
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
                binding.etName.setEnabled(true)
                binding.etGrade.setEnabled(true)
                binding.etName.setSelection(binding.etName.length())
                val imm = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
                binding.etName.requestFocus()

            }else {
                binding.changeImg.tag = 0
                binding.etName.setEnabled(false)
                binding.etGrade.setEnabled(false)
            }
        }
        binding.logoutLayout.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("로그아웃 확인")
                .setMessage("정말로 로그아웃하시겠습니까?")
                .setPositiveButton("로그아웃", DialogInterface.OnClickListener {_, _ ->
                    requireActivity().finish()
                    val intent = Intent(requireContext(), LoginActivity::class.java)
                    startActivity(intent)
                })
                .setNegativeButton("취소", DialogInterface.OnClickListener{_, _ ->

                }).show()

        }
    }
    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MyDataFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}