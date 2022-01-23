package com.example.dictionary.view.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dictionary.R
import com.example.dictionary.presenter.DataPresenterRU
import com.example.dictionary.view.common.OnListItemClickListener

class MainAdapter(
    private var onListItemClickListener: OnListItemClickListener,
    private var data: List<DataPresenterRU>
) :
    RecyclerView.Adapter<MainItemViewHolder>() {


    fun setData(data: List<DataPresenterRU>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MainItemViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recycler_view, parent, false) as View
    )

    override fun onBindViewHolder(holder: MainItemViewHolder, position: Int) {
        holder.bind(data[position])
        holder.itemView.setOnClickListener { openInNewWindow(data[position]) }
    }

    private fun openInNewWindow(listItemData: DataPresenterRU) {
        onListItemClickListener.onItemClick(listItemData)
    }

    override fun getItemCount(): Int {
        return data.size
    }
}