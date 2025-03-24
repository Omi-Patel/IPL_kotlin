package com.ipl.ipl.models

import org.springframework.data.annotation.Id
import java.time.Instant

// Model
data class Player(
    @Id val id: String? = null,
    val name: String,
    val country: String,
    val age: Int?,
    val role: String,
    val battingStyle: String,
    val bowlingStyle: String?,
    val createdAt: Long = Instant.now().toEpochMilli(),
    val updatedAt: Long = Instant.now().toEpochMilli()
)

// DTOs
data class PlayerRequest(
    val name: String,
    val country: String,
    val age: Int?,
    val role: String,
    val battingStyle: String,
    val bowlingStyle: String?
)

data class PlayerResponse(
    val id: String?,
    val name: String,
    val country: String,
    val age: Int?,
    val role: String,
    val battingStyle: String,
    val bowlingStyle: String?,
    val createdAt: Long,
    val updatedAt: Long
)