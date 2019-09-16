package com.jeevan.obvious

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.jeevan.obvious.home.response.PictureOfTheDayResponse
import kotlinx.android.synthetic.main.activity_picture_detail.*

class PictureDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picture_detail)
        val response = intent.getParcelableExtra<PictureOfTheDayResponse>(RESPONSE)!!

        supportActionBar?.apply {
            title = response.title
            setDisplayHomeAsUpEnabled(true)
            setDefaultDisplayHomeAsUpEnabled(true)
        }
        supportPostponeEnterTransition()
        setupUI(response)
    }

    private fun setupUI(response: PictureOfTheDayResponse) {
        Glide.with(this).load(response.url)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    supportStartPostponedEnterTransition()
                    return false
                }
            })
            .into(potd)
        findViewById<TextView>(R.id.title).text = response.title
        findViewById<TextView>(R.id.explanation).text = response.explanation
        findViewById<TextView>(R.id.date).text = response.date
    }

    companion object {
        private const val RESPONSE = "response"
        fun newInstance(context: Context, response: PictureOfTheDayResponse) =
            Intent(context, PictureDetailActivity::class.java).apply {
                putExtra(RESPONSE, response)
            }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home)
            finish()
        return true
    }
}
