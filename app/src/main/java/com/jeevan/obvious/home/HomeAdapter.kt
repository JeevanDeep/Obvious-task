package com.jeevan.obvious.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jeevan.obvious.R
import com.jeevan.obvious.home.response.PictureOfTheDayResponse
import com.jeevan.obvious.loadImage

class HomeAdapter(
    val list: MutableList<PictureOfTheDayResponse>,
    private val onClick: (List<PictureOfTheDayResponse>, Int) -> Unit
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val TYPE_DATA = 0
    private val TYPE_PROGRESS = 1
    var showProgress = false
    override fun getItemViewType(position: Int): Int {

        println(showProgress && position == list.size)

        if (showProgress && position == list.size) {
            return TYPE_PROGRESS
        } else {
            return TYPE_DATA
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == TYPE_PROGRESS) {
            val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.pagination_progress_layout, parent, false)
            return ProgressViewHolder(v)
        } else {
            val v =
                LayoutInflater.from(parent.context).inflate(R.layout.potd_item_view, parent, false)
            return HomeViewHolder(v)
        }
    }

    override fun getItemCount(): Int {
        if (showProgress)
            return list.size + 1
        else return list.size
    }

    fun addData(newList: List<PictureOfTheDayResponse>) {
        val size = list.size
        list.addAll(newList)
        notifyItemRangeChanged(size, list.size)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is HomeViewHolder)
            holder.bind(list[position])
    }

    inner class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val potd: ImageView = itemView.findViewById(R.id.potd)
        private val description: TextView = itemView.findViewById(R.id.description)
        fun bind(pictureOfTheDayResponse: PictureOfTheDayResponse) {
            description.text = pictureOfTheDayResponse.title
            potd.loadImage(pictureOfTheDayResponse.url)

            itemView.setOnClickListener { onClick.invoke(list, adapterPosition) }
        }
    }

    inner class ProgressViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}