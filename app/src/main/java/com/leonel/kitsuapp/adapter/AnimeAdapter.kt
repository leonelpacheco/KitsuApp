package com.leonel.kitsuapp.adapter

import android.annotation.SuppressLint
import android.content.pm.ApplicationInfo
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.leonel.kitsuapp.R
import com.leonel.kitsuapp.model.Anime
import com.squareup.picasso.Picasso
import java.util.*
import kotlin.collections.ArrayList

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

        val ItemsViewModel = mList[position]

        Picasso.get().load(ItemsViewModel.attributes.posterImage.large).into(holder.img_anime);
        if(!ItemsViewModel.attributes.titles.en.isNullOrBlank()) holder.txt_title.text = ItemsViewModel.attributes.titles.en  else holder.txt_title.text = ItemsViewModel.attributes.titles.jaJp
        holder.txt_subtitle.text = ItemsViewModel.attributes.startDate

        holder.itemView.setOnClickListener {
            onClickListener.onClick(ItemsViewModel.id)
        }

    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {

        val img_anime: ImageView = itemView.findViewById(R.id.img_anime_row)
        val txt_title: TextView = itemView.findViewById(R.id.txt_title_row)
        val txt_subtitle: TextView = itemView.findViewById(R.id.txt_subtitle_row)

    }

    fun updatefilteranimes(mListfilter: List<Anime>){
        this.mList = mListfilter
        notifyDataSetChanged()
    }
}