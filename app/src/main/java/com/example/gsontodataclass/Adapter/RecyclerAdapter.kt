package com.example.gsontodataclass.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gsontodataclass.PicsumDataClass
import com.example.gsontodataclass.R

class RecyclerAdapter(private val homeFeed: List<PicsumDataClass>) :
    RecyclerView.Adapter<CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.show_image_picsum, parent, false)
        return CustomViewHolder(cellForRow)
    }

    override fun getItemCount(): Int {
        return homeFeed.count()
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

        val photos = homeFeed[position]
        holder.tvTitle.text = photos.author

        Glide
            .with(holder.view.context)
            .load(photos.download_url)
            .into(holder.imgView)
    }
}

class CustomViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    var tvTitle: TextView = view.findViewById(R.id.title)
    var imgView: ImageView = view.findViewById(R.id.Img01)
}