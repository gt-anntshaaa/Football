package com.example.football.domain.usecase

import com.example.football.data.source.local.entity.Club
import com.example.football.domain.common.Resource
import com.example.football.domain.repository.ClubRepository
import javax.inject.Inject

class DataClubUseCase @Inject constructor(private val clubRepository: ClubRepository) {
    suspend operator fun invoke(name: String, city: String) : Resource<String>{
        return try{
            if (name.trim().isEmpty())
                throw Exception ("Club tidak valid...")
            if (city.trim().isEmpty())
                throw Exception ("City tidak valid...")

            val convert_name = name.lowercase()
            val convert_city = city.lowercase()

            val result = clubRepository.insert(Club(convert_name, convert_city))

            if (result == -1L)
                throw Exception("Data already exist")

            Resource.Success("Data berhasil di simpan")
        }catch (e: Exception){
            Resource.Failure(e.message ?: "Tidak dapat menyimpan data club...")
        }
    }
}