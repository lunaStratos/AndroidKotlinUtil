package com.lostman.basichouse.Util

import android.content.Context
import androidx.preference.PreferenceManager
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.lostman.basichouse.Vo.MemoVo


//스토리지 저장
    fun saveData(memo:MemoVo, position:Int, context:Context){

        val pref = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = pref.edit()

        var memoListStr = pref.getString("memoList", "[]")
        //Json 으로 만들기 위한 Gson
        var makeGson = GsonBuilder().create()

        var listType : TypeToken<MutableList<MemoVo>> = object : TypeToken<MutableList<MemoVo>>(){}
        var memoList : MutableList<MemoVo> = makeGson.fromJson(memoListStr, listType.type)

        if(position == -1){
            //저장
            memoList.add(memo)
        }else{
            memoList[position] = memo
        }

        // 데이터를 Json 형태로 변환
        var strContact = makeGson.toJson(memoList)

        editor.putString("memoList", strContact )
            .apply()
    }

    fun saveDataList(items:MutableList<MemoVo>, context:Context){

        val pref = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = pref.edit()

        var makeGson = GsonBuilder().create()
        var listType : TypeToken<MutableList<MemoVo>> = object : TypeToken<MutableList<MemoVo>>(){}

        // 데이터를 Json 형태로 변환
        var strContact = makeGson.toJson(items)

        editor.putString("memoList", strContact )
            .apply()
    }

    //스토리지 로드
    fun loadData(context:Context): List<MemoVo> {
        val pref = PreferenceManager.getDefaultSharedPreferences(context)
        //값 불러오기
        var memoListStr = pref.getString("memoList", "[]")

        var makeGson = GsonBuilder().create()
        var listType : TypeToken<MutableList<MemoVo>> = object : TypeToken<MutableList<MemoVo>>(){}
        var memoList : MutableList<MemoVo> = makeGson.fromJson(memoListStr, listType.type)

        if(memoList.size == 0){

        }else{
           return memoList
        }

        return memoList
    }

