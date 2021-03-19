package com.lostman.basichouse.Fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.lostman.basichouse.Adapter.ListViewAdapter
import com.lostman.basichouse.R
import com.lostman.basichouse.Util.loadData
import com.lostman.basichouse.Vo.MemoVo
import com.lostman.basichouse.databinding.FragmentMemoBinding
import com.lostman.basichouse.newMemoActivity
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.startActivityForResult
import org.jetbrains.anko.support.v4.toast

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MemoFrag.newInstance] factory method to
 * create an instance of this fragment.
 */
class MemoFrag : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentMemoBinding   //뷰바인딩을 위한 추가
    private lateinit var memoAdapter: ListViewAdapter


    var memoList = arrayListOf<MemoVo>()

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
        // Inflate the layout for this fragment
        binding = FragmentMemoBinding.inflate(layoutInflater) //뷰바인딩을 위한 추가
        val _binding = inflater.inflate(R.layout.fragment_memo, container, false)

        val context = requireActivity().applicationContext
        memoList = loadData(context) as ArrayList<MemoVo>
        //toast(memoList.toString())

        memoAdapter = ListViewAdapter(memoList)

        val memolists = binding.memoList
        memolists.adapter = memoAdapter
        binding.memoCount.text = "총 메모갯수: " + memoList.size
        binding.memoBtn.setOnClickListener {
            startActivityForResult<newMemoActivity>(1000)
        }

        memolists.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(getContext(), newMemoActivity::class.java)
            intent.putExtra("title", memoList.get(position).title)
            intent.putExtra("memo", memoList.get(position).memo)
            intent.putExtra("position", ""+position)
            startActivity(intent)
        }
        memolists.setOnItemLongClickListener { parent, view, position, id ->
            toast("위치 " + position)
            true

        }
        
        return binding.root


    }
    
    
    //갱신
    override fun onResume() {
        super.onResume()
        toast("리쥼")
        val context = requireActivity().applicationContext
        memoList = loadData(context) as ArrayList<MemoVo>
        memoAdapter = ListViewAdapter(memoList)
        memoAdapter.notifyDataSetChanged()
        binding.memoList.adapter = memoAdapter
        binding.memoCount.text = "총 메모갯수: " + memoAdapter.count

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