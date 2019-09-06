package com.muy.muysamples.recyclerview

import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.muy.muysamples.R
import kotlinx.android.synthetic.main.activity_recycler_view_sample.*

class RecyclerViewSampleActivity : AppCompatActivity() {

    private lateinit var textAdapter: TextAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view_sample)

        textAdapter = TextAdapter()

        rv_list.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = textAdapter
            addOnScrollListener(object:RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (newState == RecyclerView.SCROLL_STATE_IDLE &&
                            (layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition() == adapter!!.itemCount - 1) {
                        LoadTask(adapter as TextAdapter).execute()
                    }
                }
            })
        }

        LoadTask(textAdapter).execute()
    }

    class LoadTask(val adapter: TextAdapter) : AsyncTask<Void, Void, MutableList<String>>() {

        override fun onPreExecute() {
            super.onPreExecute()
            adapter.setStatus(STATUS_LOADING)
        }

        private var data = mutableListOf(
                "Hear the words",
                "Hear the words",
                "Hear the words",
                "Hear the words",
                "Hear the words",
                "Hear the words",
                "Hear the words",
                "Hear the words",
                "Hear the words",
                "Hear the words",
                "Hear the words",
                "Hear the words",
                "Hear the words"
        )

        override fun doInBackground(vararg params: Void?): MutableList<String> {
            Thread.sleep(2000)
            return data
        }

        override fun onPostExecute(result: MutableList<String>?) {
            adapter.addData(result!!)
            adapter.setStatus(STATUS_COMPLETE)
        }
    }
}
