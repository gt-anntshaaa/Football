package com.example.football.domain.usecase

import com.example.football.data.source.local.entity.Classement
import com.example.football.data.source.local.entity.Matches
import com.example.football.domain.common.Resource
import com.example.football.domain.repository.ClassementRepository
import com.example.football.domain.repository.ClubRepository
import com.example.football.domain.repository.MatchesRepository
import javax.inject.Inject

class MatchesUseCase @Inject constructor(private val matchesRepository: MatchesRepository,
                                         private val clubRepository: ClubRepository,
                                         private val listClubNameUseCase: ListClubNameUseCase,
                                        private val classementRepository: ClassementRepository) {
    suspend operator fun invoke(nameHost: String, nameGuest: String, hostScores: String, guestScores: String) : Resource<String>{
        return try{
//            if ((hostScore.toString().trim().isEmpty())||(guestScore.toString().trim().isEmpty()))
//                throw Exception("Score tidak valid")
//
//            if ((nameHost.trim().isEmpty())||(nameGuest.trim().isEmpty()))
//                throw Exception("Club harus diisi")
            if (nameHost.contains(nameGuest))
                throw Exception("Club tidak boleh sama")

            if (nameGuest.isBlank())
                throw Exception("Club guest tidak valid")
            if (nameHost.isBlank())
                throw Exception("Club host tidak valid")
            if (hostScores.trim().isEmpty())
                throw Exception("Score host club tidak valid")
            if (guestScores.trim().isEmpty())
                throw Exception("Score guest club tidak valid")

            val idHost = clubRepository.id(nameHost)
            val idGuest = clubRepository.id(nameGuest)

            val hostScore = hostScores.toInt()
            val guestScore = guestScores.toInt()


            matchesRepository.insert(Matches(
                idHost, idGuest,
                hostScore, guestScore
            ))

            val clubHost = clubRepository.name(idHost)
            val clubGuest = clubRepository.name(idGuest)

            val checkClassementHost = classementRepository.getAllClubName(clubHost)
            val checkClassementGuest = classementRepository.getAllClubName(clubGuest)

            val winHostValue = if (hostScore > guestScore) 1 else 0
            val winGuestValue = if (guestScore > hostScore) 1 else 0

            val lossHostValue = if(hostScore < guestScore) 1 else 0
            val lossGuestValue = if (guestScore < hostScore) 1 else 0



            var goalWinHost = 0
            var goalWinGuest = 0
            var goalLostHost = 0
            var goalLostGuest = 0

            if (hostScore > guestScore){
                goalWinHost = hostScore
                goalLostHost = guestScore

                goalWinGuest = guestScore
                goalLostGuest = hostScore
            }else if (guestScore > hostScore){
                goalWinGuest = guestScore
                goalLostGuest = hostScore

                goalWinHost = hostScore
                goalLostHost = guestScore
            }else{
                goalWinHost = hostScore
                goalWinGuest = guestScore

                goalLostHost = guestScore
                goalLostGuest = hostScore
            }

            val drawValue = if (hostScore == guestScore) 1 else 0

            var pointHost = 0
            var pointGuest = 0




            if (checkClassementHost != null){
                checkClassementHost.main += 1
                checkClassementHost.menang += winHostValue
                checkClassementHost.seri += drawValue
                checkClassementHost.kalah += lossHostValue
                checkClassementHost.goal_menang += goalWinHost
                checkClassementHost.goal_kalah += goalLostHost
                checkClassementHost.point = (checkClassementHost.menang*3) + (checkClassementHost.seri*1)
                classementRepository.update(checkClassementHost)
            }else{
                classementRepository.insert(listOf(Classement(clubHost, 1, winHostValue,
                    drawValue, lossHostValue, goalWinHost, goalLostHost,
                    point = (winHostValue*3)+(drawValue*1))))
            }

            if (checkClassementGuest != null){
                checkClassementGuest.main += 1
                checkClassementGuest.menang += winGuestValue
                checkClassementGuest.seri += drawValue
                checkClassementGuest.kalah += lossGuestValue
                checkClassementGuest.goal_menang += goalWinGuest
                checkClassementGuest.goal_kalah += goalLostGuest
                checkClassementGuest.point = ((checkClassementGuest.menang*3) + (checkClassementGuest.seri*1))
                classementRepository.update(checkClassementGuest)
            }else{
                classementRepository.insert(listOf(Classement(clubGuest, 1,
                    winGuestValue, drawValue, lossGuestValue, goalWinGuest, goalLostGuest,point = (winGuestValue*3)+(drawValue*1))))
            }

            Resource.Success("Data berhasil disimpan")
        }catch (e: Exception){
            Resource.Failure(e.message ?: "")
        }
    }
}