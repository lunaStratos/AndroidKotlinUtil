package com.lostman.basichouse

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.annotation.RequiresApi
import com.lostman.basichouse.Util.saveData
import com.lostman.basichouse.Vo.MemoVo
import com.lostman.basichouse.databinding.ActivityMainBinding
import com.lostman.basichouse.databinding.ActivityNewMemoBinding
import com.lostman.basichouse.databinding.FragmentMemoBinding
import org.jetbrains.anko.toast
import java.time.LocalDate
import java.util.*

class newMemoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewMemoBinding   //뷰바인딩을 위한 추가
    private lateinit var title:EditText
    private lateinit var memo:EditText
    private lateinit var position:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewMemoBinding.inflate(layoutInflater) //뷰바인딩을 위한 추가
        setContentView(binding.root) //뷰바인딩을 위한 추가

        title = binding.newMemoTitle
        memo = binding.newMemoText
        position = "-1"
        if (intent.hasExtra("title") && intent.hasExtra("memo") && intent.hasExtra("position")){
            val titleStr = intent.getStringExtra("title")
            val memoStr = intent.getStringExtra("memo")
            position = intent.getStringExtra("position").toString()

            title.setText(titleStr.toString())
            memo.setText(memoStr.toString())
        }


    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBackPressed() {
        super.onBackPressed()

        binding = ActivityNewMemoBinding.inflate(layoutInflater) //뷰바인딩을 위한 추가
        setContentView(binding.root)

        //저장

        val date = LocalDate.now().toString()

        if(title.text.toString().isNotEmpty()){
            val newMemo = MemoVo(title.text.toString(), memo.text.toString(), date)

            if (position != null){
                if(position.isNotBlank()){
                    saveData(newMemo, position.toInt(), this)
                }

            }else{

                saveData(newMemo, -1, this)
            }
        }




    }
}