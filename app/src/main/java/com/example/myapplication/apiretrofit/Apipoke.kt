package com.example.myapplication.apiretrofit



import com.example.myapplication.apiretrofit.pokedetail.pDetail
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ApipokeDetail {
    //https://pokeapi.co/api/v2/pokemon/$name
    @GET("pokemon/{id}")
    fun getDetail(@Path(value = "id", encoded = true) id: Int): Call<pDetail>
}
object RetrofitPokeDetail {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val API_Poke:ApipokeDetail by lazy {
        retrofit.create(ApipokeDetail::class.java)
    }
}
