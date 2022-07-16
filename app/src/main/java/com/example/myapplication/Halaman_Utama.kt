package com.example.myapplication


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.apiretrofit.RetrofitPokeDetail
import com.example.myapplication.apiretrofit.pokedetail.pDetail
import com.example.myapplication.databinding.ActivityUtamaBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Halaman_Utama : AppCompatActivity() {
    private lateinit var binding: ActivityUtamaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivityUtamaBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val apiPokeDtl = RetrofitPokeDetail.API_Poke
        for (id in 1..151) {
            apiPokeDtl.getDetail(id).enqueue(object : Callback<pDetail> {
                @SuppressLint("SetTextI18n")
                override fun onResponse(call: Call<pDetail>, response: Response<pDetail>) {
                    if (response.isSuccessful) {
                        val pokedetail = response.body()
                        if (pokedetail != null) {
                            PNameList.nameList.add(pokedetail)
                            println("detdetdetdet : ${pokedetail.id},${pokedetail.name} , loop id $id")
                            binding.info.text = "Getting Pokemon Data \n$id of 151 \nPlease Wait !"
                            showRecyclerList(PNameList.nameList)

                            if (id == 151) {
                                binding.load.isVisible = false
                                binding.info.isVisible = false
                                binding.Plist.isVisible = true
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<pDetail>, t: Throwable) {
                    Toast.makeText(
                        applicationContext,
                        "Gagal mengambil data, periksa koneksi internet anda!",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            })
        }
        binding.FavButton.setOnClickListener {
            if (binding.FavButton.isChecked){
                if (PNameList.favList.isNotEmpty()){
                    showRecyclerList(PNameList.favList)
                }else{
                    binding.FavButton.isChecked = false
                    Toast.makeText(
                        applicationContext,
                        "Tidak ada data !! Tambahkan beberapa pokemon terlebih dahulu",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                showRecyclerList(PNameList.nameList)
            }
        }
        binding.profbutton.setOnClickListener {
            startActivity(Intent(applicationContext, Halaman_About::class.java))
        }


    }

    private fun showRecyclerList(list:  MutableList<pDetail>) {
        PNameList.nameList.sortBy { it.id }
        PNameList.favList.sortBy { it.id }
        binding.Plist.layoutManager = GridLayoutManager(this, 3)
        binding.Plist.adapter =
            PokeListAdapter(list,PNameList.favList, object : PokeListAdapter.onSelectPokeListener {
                override fun onSelectedPoke(data: pDetail) {

                    val type1: String
                    val type2: String
                    if (data.types.size == 2) {
                        type1 = data.types[0].type.name
                        type2 = data.types[1].type.name
                    } else {
                        type1 = data.types[0].type.name
                        type2 = data.types[0].type.name
                    }

                    startActivity(
                        Intent(applicationContext, Halaman_Detail::class.java)
                            .putExtra("PName", data.name)
                            .putExtra("PImg", data.sprites.front_default)
                            .putExtra("PType1", type1)
                            .putExtra("PType2", type2)
                            .putExtra("PId", data.id)
                            .putExtra("PWeight", data.weight)
                            .putExtra("PHeight", data.height)
                            .putExtra("PHp", data.stats[0].base_stat)
                            .putExtra("PAtk", data.stats[1].base_stat)
                            .putExtra("PDef", data.stats[2].base_stat)
                            .putExtra("PSpAtk", data.stats[3].base_stat)
                            .putExtra("PSpDef", data.stats[4].base_stat)
                            .putExtra("PSpd", data.stats[5].base_stat)
                            .putExtra("PAbility", data.abilities[0].ability.name)
                    )

                }

            }, object : PokeListAdapter.OnFavListener {
                //            Toast.makeText(applicationContext, "Kamu tidak memilih ", Toast.LENGTH_SHORT).show()
                override fun onFav(data: pDetail, state: Boolean){
                    if (state){
                        PNameList.favList.add(data)
                    }else {
                        PNameList.favList.remove(data)
                    }

                }

            })

    }

}



