package com.lostman.basichouse.Api

import com.lostman.basichouse.Vo.lotto
import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.Deferred
import okhttp3.Response
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface apiMap {

    //get방식 파라메터는 Query로 한다
    @GET("common.do")
    fun getLotto(
        @Query("method") getLottoNumber:String
    ,      @Query("drwNo") num:String
    ): Call<lotto>

    //get방식 파라메터는 Query로 한다
    @GET("common.do")
    fun getLotto2(
        @Query("method") getLottoNumber:String
        ,      @Query("drwNo") num:String
    ): Observable<lotto>

    //String 값 return
    @POST("json")
    fun getPost(
    ): Observable<String>

    //GET방식 주소 입력은 Path
    @GET("/posts/{userId}")
    fun getFirst(@Path("userId") id:String): Call<lotto>



}