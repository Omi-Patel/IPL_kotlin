package com.ipl.ipl.services

import com.ipl.ipl.models.Player
import com.ipl.ipl.models.PlayerRequest
import com.ipl.ipl.models.PlayerResponse
import com.ipl.ipl.repositories.PlayerRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class PlayerService(private val playerRepository: PlayerRepository) {

    val id = UUID.randomUUID().toString()
    val createdAt = System.currentTimeMillis()
    val updatedAt = System.currentTimeMillis()

    fun createPlayer(request: PlayerRequest): PlayerResponse {
        val player = Player(
            name = request.name,
            country = request.country,
            age = request.age,
            role = request.role,
            battingStyle = request.battingStyle,
            bowlingStyle = request.bowlingStyle
        )
        return playerRepository.save(player, id, createdAt, updatedAt).toResponse()
    }

    fun getAllPlayers(): List<PlayerResponse> =
        playerRepository.findAll().map { it.toResponse() }

    fun getPlayerById(id: String): PlayerResponse? =
        playerRepository.findById(id)?.toResponse()

    fun deletePlayer(id: Int) = playerRepository.deleteById(id)

    private fun Player.toResponse() = PlayerResponse(
        id, name, country, age, role, battingStyle, bowlingStyle, createdAt, updatedAt
    )
}