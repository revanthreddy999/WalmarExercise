package com.example.walmartexercise.view

import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.walmartexercise.R
import com.example.walmartexercise.model.Products
import com.example.walmartexercise.viewModel.MainActivityViewModel
import kotlinx.coroutines.Dispatchers
import java.util.*

class MainActivity : AppCompatActivity() {
    private val MAX_ITEMS_PER_REQUEST: Int= 3
    var toolbar: Toolbar? = null
    var recyclerView: RecyclerView? = null
    var progressBar: ProgressBar? = null
    private var layoutManager: LinearLayoutManager? = null
    private var items: List<Products>? = null
    private var page = 0
    lateinit var context: Context

    private lateinit var mainActivityViewModel: MainActivityViewModel
     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
         context = this@MainActivity

         mainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)


         items = createItems()

        initViews()
        initRecyclerView()
        setSupportActionBar(toolbar)

    }

    private fun initViews() {
        toolbar = findViewById<View>(R.id.toolbar) as Toolbar?
        recyclerView = findViewById<View>(R.id.recycler_view) as RecyclerView?
        progressBar = findViewById<View>(R.id.progress_bar) as ProgressBar?
    }

    private fun initRecyclerView() {
        layoutManager = LinearLayoutManager(this)
        recyclerView?.setHasFixedSize(true)
        recyclerView?.layoutManager = layoutManager
        recyclerView?.adapter = MyAdapter(items!!.subList(page, MAX_ITEMS_PER_REQUEST))
        recyclerView?.addOnScrollListener(createInfiniteScrollListener())
    }

    private fun createInfiniteScrollListener(): InfiniteScrollListener {
        return object : InfiniteScrollListener(MAX_ITEMS_PER_REQUEST, layoutManager) {
            override fun onScrolledToEnd(firstVisibleItemPosition: Int) {
                simulateLoading()
                val start = ++page * MAX_ITEMS_PER_REQUEST
                val allItemsLoaded = start >= items!!.size
                if (allItemsLoaded) {
                    progressBar?.visibility = View.GONE
                } else {
                    val end = start + MAX_ITEMS_PER_REQUEST
                    val itemsLocal = getItemsToBeLoaded(start, end)
                    recyclerView?.let {
                        refreshView(
                            it,
                            MyAdapter(itemsLocal), firstVisibleItemPosition)
                    }
                }
            }
        }
    }

    private fun simulateLoading() {
    }

    private fun getItemsToBeLoaded(start: Int, end: Int): List<Products> {
        val newItems = items!!.subList(start, end)
        val oldItems = (recyclerView?.adapter as MyAdapter).items
        val itemsLocal: MutableList<Products> = LinkedList()
        itemsLocal.addAll(oldItems)
        itemsLocal.addAll(newItems)
        return itemsLocal
    }



        private fun createItems(): List<Products> {
            val itemsLocal: MutableList<Products> = LinkedList()
            mainActivityViewModel.getUser()?.observe(this, androidx.lifecycle.Observer { walmartResponse->
                walmartResponse.products
            })
            return itemsLocal
        }

}