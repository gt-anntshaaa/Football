package com.example.football.domain.usecase

import com.example.football.domain.repository.ClubRepository
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class ListClubNameUseCase @Inject constructor(private val clubRepository: ClubRepository) {
    suspend operator fun invoke() : List<String>{
        return try{
            var list = emptyList<String>()
            clubRepository.fetchName().collect{
                list = it
            }

            list
        }catch (e: Exception){
            emptyList<String>()
        }
    }
}