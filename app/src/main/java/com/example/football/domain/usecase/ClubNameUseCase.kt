package com.example.football.domain.usecase

import com.example.football.domain.common.Resource
import com.example.football.domain.repository.ClubRepository
import javax.inject.Inject

class ClubNameUseCase @Inject constructor(private val clubRepository: ClubRepository) {
    suspend operator fun invoke() : Resource<Array<String>>{
        return try {
            var listClubName = emptyArray<String>()
            clubRepository.fetchClub().collect{
                listClubName = it
            }

            Resource.Success(listClubName)
        }catch (e: Exception){
            Resource.Failure("No Data")
        }
    }
}