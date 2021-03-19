package com.lostman.basichouse.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lostman.basichouse.Api.connect
import com.lostman.basichouse.R
import com.lostman.basichouse.databinding.FragmentLottoBinding

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import org.jetbrains.anko.support.v4.toast

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LottoFrag.newInstance] factory method to
 * create an instance of this fragment.
 */
class LottoFrag : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var binding: FragmentLottoBinding   //뷰바인딩을 위한 추가

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

        binding = FragmentLottoBinding.inflate(layoutInflater) //뷰바인딩을 위한 추가
        binding.lottoBtn.setOnClickListener {

            val url = "https://www.dhlottery.co.kr/"
            val num = binding.lottoNum.text


            val api = connect(url)
            api.getLotto2("getLottoNumber", num.toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {lotto ->
                        val body = lotto!!
                        val nowLotto = "당첨번호 :" +  body.drwtNo1 + "/" +  body.drwtNo2 + "/"+ body.drwtNo3 + "/" +  body.drwtNo4 + "/" +  body.drwtNo5 + "/" +  body.drwtNo6 + "/"
                        val price = "1등 당첨액수 :" + body.firstWinamnt
                        val priceNum = "1등 당첨자 수 :" + body.firstPrzwnerCo

                        binding.lottoResult.text = nowLotto
                        binding.lottoPriceResult.text = price
                        binding.lottoPriceNumResult.text = priceNum
                    },
                    {error -> toast(error.toString())}
                )

        }
        val _binding = inflater.inflate(R.layout.fragment_lotto, container, false)

        return binding.root


    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LottoFrag.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LottoFrag().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}