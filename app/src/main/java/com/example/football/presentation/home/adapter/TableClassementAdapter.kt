package com.example.football.presentation.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.football.data.source.local.entity.Classement
import com.example.football.databinding.TableRowLayoutBinding
import javax.inject.Inject

class TableClassementAdapter @Inject constructor() : ListAdapter<Classement, TableClassementViewHolder>(ClassementComparator()) {


    class ClassementComparator : DiffUtil.ItemCallback<Classement>(){
        override fun areItemsTheSame(oldItem: Classement, newItem: Classement): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Classement, newItem: Classement): Boolean {
            return oldItem.idClassement == newItem.idClassement
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TableClassementViewHolder {
        return TableClassementViewHolder(
            TableRowLayoutBinding.inflate(LayoutInflater.from(parent.context),
                parent, false)
        )
    }


    override fun onBindViewHolder(holder: TableClassementViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}