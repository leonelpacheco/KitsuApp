package com.leonel.kitsuapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.leonel.kitsuapp.R
import com.leonel.kitsuapp.model.Anime
import com.squareup.picasso.Picasso


class AnimeAdapter(
    private var mList: List<Anime>,
    private var onClickListener: OnAnimeClickListener
    ):RecyclerView.Adapter<AnimeAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_anime, parent, false)
        return ViewHolder(view)
    }
    //interface para pasar el Id al Fragment
    interface OnAnimeClickListener {
        fun onClick(idAnime: String)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val itemsViewModel = mList[position]

        Picasso.get().load(itemsViewModel.attributes.posterImage.large).into(holder.imganime)
        if(!itemsViewModel.attributes.titles.en.isNullOrBlank()) holder.txttitle.text = itemsViewModel.attributes.titles.en  else holder.txttitle.text = itemsViewModel.attributes.titles.jaJp
        holder.txtsubtitle.text = itemsViewModel.attributes.startDate

        holder.itemView.setOnClickListener {
            onClickListener.onClick(itemsViewModel.id)
        }

    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {

        val imganime: ImageView = itemView.findViewById(R.id.img_anime_row)
        val txttitle: TextView = itemView.findViewById(R.id.txt_title_row)
        val txtsubtitle: TextView = itemView.findViewById(R.id.txt_subtitle_row)

    }

    fun updatefilteranimes(mListfilter: List<Anime>){
        this.mList = mListfilter
        notifyDataSetChanged()
    }
}