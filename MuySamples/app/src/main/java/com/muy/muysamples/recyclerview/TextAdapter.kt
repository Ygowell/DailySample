package com.muy.muysamples.recyclerview

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.muy.muysamples.R

const val STATUS_LOADING = 1
const val STATUS_COMPLETE = 2

class TextAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var dataList = mutableListOf<String>()
    private var status = STATUS_COMPLETE

    fun addData(list: MutableList<String>) {
        val insertIndex = dataList.size
        dataList.addAll(list)
        notifyItemRangeInserted(insertIndex, list.size)
    }

    fun setStatus(status: Int) {
        if (dataList.size > 0) {
            this.status = status
            notifyItemRangeChanged(itemCount - 1, 1)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            itemCount - 1 -> 0
            else -> 1
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            0 -> LoadHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_load, parent, false))
            else -> TextHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false))
        }
    }

    override fun getItemCount() = if (dataList.size > 0) dataList.size + 1 else 0

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            0 -> (holder as LoadHolder).bind(status)
            else -> (holder as TextHolder).bind("$position - ${dataList[position]}")
        }
    }

    inner class TextHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val titleTv: TextView = view.findViewById(R.id.tv_title)

        fun bind(data: String) {
            titleTv.text = data
        }
    }

    inner class LoadHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(status: Int) {
            if (status == STATUS_LOADING) {
                itemView.layoutParams.height = 200
            } else {
                itemView.layoutParams.height = 1
            }
        }
    }
}