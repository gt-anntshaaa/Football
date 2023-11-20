package com.example.football.presentation.home.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.football.data.source.local.entity.Classement
import com.example.football.databinding.TableRowLayoutBinding

class TableClassementViewHolder(private val binding: TableRowLayoutBinding) :
    RecyclerView.ViewHolder(binding.root) {

        fun bind(data : Classement){
            binding.tvClub.text = data.club
            binding.tvMain.text = data.main.toString()
            binding.tvMenang.text = data.menang.toString()
            binding.tvSeri.text = data.seri.toString()
            binding.tvKalah.text = data.kalah.toString()
            binding.tvGoalMenang.text = data.goal_menang.toString()
            binding.tvGoalKalah.text = data.goal_kalah.toString()
            binding.tvPoin.text = data.point.toString()
        }
}