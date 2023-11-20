package com.example.football.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "club", indices = [Index("name", unique = true)])
data class Club(
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "city") val city: String
){
    @PrimaryKey(autoGenerate = true) var idClub: Int = 0
}
