package com.example.myapplication


import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.myapplication.databinding.ActivityDetailBinding
import com.squareup.picasso.Picasso

class Halaman_Detail : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnback.setOnClickListener{
            finish()
        }

        val name = intent.getStringExtra("PName")
        val type1 = intent.getStringExtra("PType1")
        val type2 = intent.getStringExtra("PType2")
        val imgUrl = intent.getStringExtra("PImg")
        val weight = intent.getIntExtra("PWeight",0).toString()
        val height = intent.getIntExtra("PHeight",0).toString()
        val pokeWeight = "$weight kg"
        val pokeHeight = "$height m"
        val ability = intent.getStringExtra("PAbility")
        val id = intent.getIntExtra("PId",0).toString()
        val pokeId: String = if (id.length==1) "#00$id" else "#0$id"
        val hp = intent.getIntExtra("PHp",0).toString()
        val atk = intent.getIntExtra("PAtk",0).toString()
        val def = intent.getIntExtra("PDef",0).toString()
        val spatk = intent.getIntExtra("PSpAtk",0).toString()
        val spdef = intent.getIntExtra("PSpDef",0).toString()
        val spd = intent.getIntExtra("PSpd",0).toString()

        binding.root.setBackgroundColor(layBgColor(type1))
        binding.PkType1.setCardBackgroundColor(layBgColor(type1))
        binding.PkType2.setCardBackgroundColor(layBgColor(type2))
        binding.PkType2.preventCornerOverlap = true
        binding.Pktype1Text.text = type1
        binding.Pktype2Text.text = type2
        binding.PKName.text = name
        binding.PKid.text = pokeId
        binding.PkWeight.text = pokeWeight
        binding.PkHeight.text = pokeHeight
        binding.PkAbility.text = ability
        binding.PHp.text = hp
        binding.PAtk.text = atk
        binding.PDef.text = def
        binding.PSpAtk.text = spatk
        binding.PSpDef.text = spdef
        binding.PSpd.text = spd
        binding.progressHP.progress = hp.toInt()
        binding.progressAtk.progress = atk.toInt()
        binding.progressDef.progress = def.toInt()
        binding.progressSpAtk.progress = spatk.toInt()
        binding.progressSpDef.progress = spdef.toInt()
        binding.progressSpd.progress = spd.toInt()
        Picasso.with(binding.PKImg.context).load(imgUrl).into(binding.PKImg)


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
            binding.share.revealOnFocusHint = true
        }
        binding.share.setOnClickListener {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "Pokemon Data \n " +
                        "\nID : $id" +
                        "\nName : $name" +
                        "\ntype : ${type1},${type2}" +
                        "\nSTAT : HP: $hp | ATK: $atk | DEF $def | SPATK $spatk | SPDEF $spdef | SPD $spd")
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)

        }
    }

    private fun layBgColor(color: String?): Int {
        when(color){
            "grass" -> {
                    return ContextCompat.getColor(this, R.color.grass)
            }"poison"->{
                    return ContextCompat.getColor(this, R.color.poison )
            }"normal"->{
                    return ContextCompat.getColor(this, R.color.normal )
            }"bug"->{
                    return ContextCompat.getColor(this, R.color.bug )
            }"electric"->{
                    return ContextCompat.getColor(this, R.color.electric )
            }"fire"->{
                    return ContextCompat.getColor(this, R.color.fire )
            }"water"->{
                    return ContextCompat.getColor(this, R.color.water )
            }"ground"->{
                    return ContextCompat.getColor(this, R.color.ground )
            }"flying"->{
                    return ContextCompat.getColor(this, R.color.flying )
            }"dark" -> {
                return ContextCompat.getColor(this, R.color.dark)
            }"dragon"->{
                return ContextCompat.getColor(this, R.color.dragon )
            }"steel"->{
                return ContextCompat.getColor(this, R.color.steel )
            }"rock"->{
                return ContextCompat.getColor(this, R.color.rock )
            }"fairy"->{
                return ContextCompat.getColor(this, R.color.fairy )
            }"fighting"->{
                return ContextCompat.getColor(this, R.color.fighting )
            }"ghost"->{
                return ContextCompat.getColor(this, R.color.ghost )
            }"psychic"->{
                return ContextCompat.getColor(this, R.color.psychic )
            }"ice"->{
                return ContextCompat.getColor(this, R.color.ice )
            }

        }
        return ContextCompat.getColor(this, R.color.normal )
    }
}