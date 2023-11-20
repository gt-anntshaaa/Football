package com.example.football.domain.usecase

import com.example.football.data.source.local.entity.Classement
import com.example.football.domain.common.Resource
import com.example.football.domain.repository.ClassementRepository
import javax.inject.Inject

class LoadClassementUseCase @Inject constructor(
    private val classementRepository: ClassementRepository,
) {
    suspend operator fun invoke() : Resource<List<Classement>> {
        return try{
            var allClassement = emptyList<Classement>()
            classementRepository.fetchClassement().collect{
                allClassement = it
            }

            Resource.Success(allClassement)
        }catch (e: Exception){
            Resource.Failure(e.message ?: "error during load classement")
        }
    }
}