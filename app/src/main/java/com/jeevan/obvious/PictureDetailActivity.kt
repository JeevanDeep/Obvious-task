package com.jeevan.obvious

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.jeevan.obvious.home.response.PictureOfTheDayResponse
import kotlinx.android.synthetic.main.activity_picture_detail.*

class PictureDetailActivity : AppCompatActivity() {

    private lateinit var response: ArrayList<PictureOfTheDayResponse>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picture_detail)
        response = intent.getParcelableArrayListExtra<PictureOfTheDayResponse>(RESPONSE)!!
        val position = intent.getIntExtra(POSITION, 0)

        supportActionBar?.apply {
            title = response[position].title
            setDisplayHomeAsUpEnabled(true)
            setDefaultDisplayHomeAsUpEnabled(true)
        }

        viewerModeViewPager.adapter = ViewerModeAdapter(response)
        viewerModeViewPager.setCurrentItem(position, false)
        viewerModeViewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                supportActionBar?.title = response[position].title
            }
        })
    }


    companion object {
        private const val RESPONSE = "response"
        const val POSITION = "position"
        fun newInstance(
            context: Context,
            response: ArrayList<PictureOfTheDayResponse>,
            position: Int
        ) =
            Intent(context, PictureDetailActivity::class.java).apply {
                putParcelableArrayListExtra(RESPONSE, response)
                putExtra(POSITION, position)
            }
    }

    override fun onBackPressed() {
        Intent().apply {
            putExtra(POSITION, viewerModeViewPager.currentItem)
            setResult(Activity.RESULT_OK, this)
        }
        super.onBackPressed()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home)
            onBackPressed()
        return true
    }
}
