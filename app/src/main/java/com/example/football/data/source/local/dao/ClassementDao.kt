package com.example.football.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.football.data.source.local.entity.Classement

@Dao
interface ClassementDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(classement: List<Classement>)

    @Query("SELECT * FROM classement")
    suspend fun fetchClassement() : List<Classement>

    @Query("SELECT * FROM classement WHERE club = :name LIMIT 1")
    suspend fun getAllClubName(name: String) : Classement?

    @Update
    suspend fun update(classement: Classement)
}