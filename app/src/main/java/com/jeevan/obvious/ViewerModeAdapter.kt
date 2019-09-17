package com.jeevan.obvious

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jeevan.obvious.home.response.PictureOfTheDayResponse

class ViewerModeAdapter(private val list: List<PictureOfTheDayResponse>) :
    RecyclerView.Adapter<ViewerModeAdapter.ViewerModeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewerModeViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.viewer_mode_item_view, parent, false)
        return ViewerModeViewHolder(v)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewerModeViewHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class ViewerModeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val potd = itemView.findViewById<ImageView>(R.id.potd)
        val title = itemView.findViewById<TextView>(R.id.title)
        val explanation = itemView.findViewById<TextView>(R.id.explanation)
        val date = itemView.findViewById<TextView>(R.id.date)
        fun bind(response: PictureOfTheDayResponse) {
            title.text = response.title
            explanation.text = response.explanation
            date.text = response.date
            Glide.with(itemView.context).load(response.url)
                .into(potd)
        }
    }
}