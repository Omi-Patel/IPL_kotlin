package com.ipl.ipl.controllers

import com.ipl.ipl.models.PlayerRequest
import com.ipl.ipl.models.PlayerResponse
import com.ipl.ipl.services.PlayerService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/players")
class PlayerController(private val playerService: PlayerService) {

    @PostMapping("/create")
    fun createPlayer(@RequestBody request: PlayerRequest): ResponseEntity<PlayerResponse> =
        ResponseEntity.ok(playerService.createPlayer(request))

    @GetMapping("/list")
    fun getAllPlayers(): ResponseEntity<List<PlayerResponse>> =
        ResponseEntity.ok(playerService.getAllPlayers())

    @GetMapping("/get/{id}")
    fun getPlayerById(@PathVariable id: String): ResponseEntity<PlayerResponse> =
        playerService.getPlayerById(id)?.let { ResponseEntity.ok(it) } ?: ResponseEntity.notFound().build()

    @DeleteMapping("/delete/{id}")
    fun deletePlayer(@PathVariable id: Int): ResponseEntity<Void> {
        playerService.deletePlayer(id)
        return ResponseEntity.noContent().build()
    }
}
