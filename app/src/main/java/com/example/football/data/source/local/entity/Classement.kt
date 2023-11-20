package com.example.football.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "classement")
data class Classement(
    @ColumnInfo(name = "club")
    val club: String,
    @ColumnInfo(name = "main")
    var main: Int = 0,
    @ColumnInfo(name = "menang")
    var menang: Int = 0,
    @ColumnInfo(name = "seri")
    var seri: Int = 0,
    @ColumnInfo(name = "kalah")
    var kalah: Int = 0,
    @ColumnInfo(name = "goal_menang")
    var goal_menang: Int = 0,
    @ColumnInfo(name = "goal_kalah")
    var goal_kalah: Int = 0,
    @ColumnInfo(name = "point")
    var point: Int = 0
){
    @PrimaryKey(autoGenerate = true) var idClassement: Int = 0
}
