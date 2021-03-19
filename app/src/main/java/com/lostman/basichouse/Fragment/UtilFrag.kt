package com.lostman.basichouse.Fragment

import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import com.lostman.basichouse.R
import com.lostman.basichouse.Util.Torch
import com.lostman.basichouse.databinding.FragmentMemoBinding
import com.lostman.basichouse.databinding.FragmentUtilBinding
import kotlin.properties.Delegates

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MemoFrag.newInstance] factory method to
 * create an instance of this fragment.
 */
class UtilFrag : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    private lateinit var binding: FragmentUtilBinding   //뷰바인딩을 위한 추가
    private var torchMode by Delegates.notNull<Boolean>()
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUtilBinding.inflate(layoutInflater) //뷰바인딩을 위한 추가
        val _binding = inflater.inflate(R.layout.fragment_memo, container, false)

        val lightBtn = binding.lightBtn
        torchMode = false

        lightBtn.setOnClickListener {
            val torch = context?.let { it1 -> Torch(it1) }
            if (torch != null){
                if (torchMode) {
                    torch.flashOn()
                } else {
                    torch.flashOff()
                }
            }


        }


        return binding.root


    }



    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MemoFrag.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MemoFrag().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}