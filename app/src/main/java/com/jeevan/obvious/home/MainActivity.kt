package com.jeevan.obvious.home

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jeevan.obvious.ObviousApp
import com.jeevan.obvious.PictureDetailActivity
import com.jeevan.obvious.R
import com.jeevan.obvious.di.ViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val homeViewmodel: HomeViewModel by viewModels { viewModelFactory }
    private lateinit var adapter: HomeAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ObviousApp.applicationComponent.inject(this)
        setupAdapter()
        homeViewmodel.getPotd()

        supportActionBar?.title = "Home"
        homeViewmodel.potdResponse.observe(this) { list ->
            progressBar.visibility = View.GONE
            if (list.isEmpty() && potdRecyclerView.layoutManager?.itemCount == 0) {
                errorText.visibility = View.VISIBLE
            } else {
                adapter.showProgress = homeViewmodel.canRequestMore
                adapter.addData(list)
            }
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        homeViewmodel.saveState(adapter.list)
    }

    private fun setupAdapter() {
        adapter =
            HomeAdapter(mutableListOf(), onClick = { list, position ->
                startActivityForResult(
                    PictureDetailActivity.newInstance(
                        this,
                        ArrayList(list),
                        position
                    ), VIEWER_MODE_CODE
                )
            })
        potdRecyclerView.adapter = adapter

        potdRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val lm = recyclerView.layoutManager as GridLayoutManager
                val itemCount = lm.itemCount
                if (itemCount == 0)
                    return
                val lastVisiblePosition = lm.findLastCompletelyVisibleItemPosition()
                val canRequestMore =
                    lastVisiblePosition == itemCount - 1 && homeViewmodel.canRequestMore
                if (canRequestMore)
                    homeViewmodel.getPotd()
            }
        })

        (potdRecyclerView.layoutManager as GridLayoutManager).spanSizeLookup =
            object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    if (adapter.showProgress && position == potdRecyclerView.layoutManager!!.itemCount - 1) {
                        return 2
                    } else {
                        return 1
                    }
                }
            }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == VIEWER_MODE_CODE && resultCode == Activity.RESULT_OK) {
            val position = data?.getIntExtra(PictureDetailActivity.POSITION, 0) ?: 0
            potdRecyclerView.scrollToPosition(position)
        }
    }

    companion object {
        private const val VIEWER_MODE_CODE = 1709
    }
}
