package com.example.football.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.football.data.source.local.entity.Club
import kotlinx.coroutines.flow.Flow

@Dao
interface ClubDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertClub(club: Club) : Long

    @Query("SELECT name FROM club ORDER BY name ASC")
    suspend fun fetchClub() : Array<String>

    @Query("SELECT idClub FROM club WHERE name = :name LIMIT 1")
    suspend fun getIdClubByName(name: String): Int

    @Query("SELECT name FROM club WHERE idClub = :id")
    suspend fun getNameClubById(id: Int) : String

    @Query("SELECT name FROM club")
    suspend fun fetchName() : List<String>
}