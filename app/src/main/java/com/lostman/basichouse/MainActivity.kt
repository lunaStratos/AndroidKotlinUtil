package com.lostman.basichouse

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.lostman.basichouse.Fragment.LottoFrag
import com.lostman.basichouse.Fragment.MemoFrag
import com.lostman.basichouse.Fragment.UtilFrag
import com.lostman.basichouse.databinding.ActivityMainBinding
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {
    /**
     * 1. 메모
     * 2. 그림뷰
     * 3. 로또 번호 생성 및 현재로또 보기
     * 3. Util 기능들     * 4. 새 엑티비티

     * 5. 역 엑티비티
     * 6.
     */

    private lateinit var binding: ActivityMainBinding   //뷰바인딩을 위한 추가

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater) //뷰바인딩을 위한 추가
        setContentView(binding.root) //뷰바인딩을 위한 추가

        val menuBar = binding.menuBar



        menuBar.setOnNavigationItemSelectedListener {
           when(it.itemId){
               R.id.memoMenu -> changeFragment(MemoFrag())
               R.id.utilMenu -> changeFragment(UtilFrag())
               R.id.lottoItem -> changeFragment(LottoFrag())
           }

           true
        }
        
        //초기값
        menuBar.selectedItemId = R.id.memoMenu


    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frameView, fragment)
            .commit()
    }

}