package com.example.myapplication


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.apiretrofit.pokedetail.pDetail
import com.squareup.picasso.Picasso


class PokeListAdapter(private val listPoke:
                      MutableList<pDetail>,private val listFav:MutableList<pDetail>, private val itemPokeListener: onSelectPokeListener
                      ,private val itemFavListener: OnFavListener): RecyclerView.Adapter<PokeListAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var pokeNameView: TextView = itemView.findViewById(R.id.pokename)
        var pokeImgView: ImageView = itemView.findViewById(R.id.pokeimg)
        var pokeCardView: CardView = itemView.findViewById(R.id.cardpoke)
        var pokeFavView: CheckBox = itemView.findViewById(R.id.fav)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.adapter_main, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = listPoke[position]
        if (listFav.isNotEmpty()){
            holder.pokeFavView.isChecked = data in listFav
        }

//        if (listFav.contains(data)) holder.pokeFavView.isChecked = true
        holder.pokeNameView.text = data.name
        Picasso.with(holder.pokeImgView.context).load(data.sprites.front_default).into(holder.pokeImgView)



        when (data.types[0].type.name) {
            "grass" -> {
                holder.pokeCardView.setCardBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.grass))
            }
            "water" -> {
                holder.pokeCardView.setCardBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.water))
            }
            "fire" -> {
                holder.pokeCardView.setCardBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.fire))
            }
            "electric" -> {
                holder.pokeCardView.setCardBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.electric))
            }
            "ground" -> {
                holder.pokeCardView.setCardBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.ground))
            }
            "bug" -> {
                holder.pokeCardView.setCardBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.bug))
            }
            "poison" -> {
                holder.pokeCardView.setCardBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.poison))
            }
            "normal" -> {
                holder.pokeCardView.setCardBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.normal))
            }
            "dark" -> {
                holder.pokeCardView.setCardBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.dark))
            }
            "dragon" -> {
                holder.pokeCardView.setCardBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.dragon))
            }
            "psychic" -> {
                holder.pokeCardView.setCardBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.psychic))
            }
            "ice" -> {
                holder.pokeCardView.setCardBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.ice))
            }
            "fairy" -> {
                holder.pokeCardView.setCardBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.fairy))
            }
            "fighting" -> {
                holder.pokeCardView.setCardBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.fighting))
            }
            "ghost" -> {
                holder.pokeCardView.setCardBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.ghost))
            }
            "rock" -> {
                holder.pokeCardView.setCardBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.rock))
            }
            "steel" -> {
                holder.pokeCardView.setCardBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.steel))
            }
            "flying" -> {
                holder.pokeCardView.setCardBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.flying))
            }
        }
        holder.pokeFavView.setOnClickListener{
            val state = holder.pokeFavView.isChecked
            itemFavListener.onFav(data,state)
        }
        holder.itemView.setOnClickListener { itemPokeListener.onSelectedPoke(data) }
    }
    override fun getItemCount(): Int {
        return listPoke.size
    }
    interface onSelectPokeListener{
        fun onSelectedPoke(data: pDetail)
    }
    interface OnFavListener{
        fun onFav(data: pDetail,state: Boolean)
    }
}

