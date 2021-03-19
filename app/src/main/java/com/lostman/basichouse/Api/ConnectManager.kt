package com.lostman.basichouse.Api

import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class connectManager{

}

//json으로 값 return
fun connect(url:String): apiMap {
    val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()
    val api = retrofit.create(apiMap::class.java)
    return api
}

//일반 String으로 값 return
fun connectStr(url:String): apiMap {
    val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    val api = retrofit.create(apiMap::class.java)
    return api
}

