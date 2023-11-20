package com.example.football.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "matches")
data class Matches(
    @ColumnInfo(name = "host_team_id")
    val hostTeamId: Int,
    @ColumnInfo(name = "guest_team_id")
    val guestTeamId: Int,
    @ColumnInfo(name = "host_score")
    val hostScore: Int,
    @ColumnInfo(name = "guest_score")
    val guestScore: Int
){
    @PrimaryKey(autoGenerate = true) var idMatches: Int = 0
}
