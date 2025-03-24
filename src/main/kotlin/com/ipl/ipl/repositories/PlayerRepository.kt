package com.ipl.ipl.repositories

import com.ipl.ipl.models.Player
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class PlayerRepository(private val jdbcTemplate: JdbcTemplate) {

    fun save(player: Player, id: String, createdAt: Long, updatedAt: Long): Player {
        val sql = """
    INSERT INTO players (id, name, country, age, role, batting_style, bowling_style, created_at, updated_at)
    VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
    """.trimIndent()

        jdbcTemplate.update(
            sql, id, player.name, player.country, player.age, player.role,
            player.battingStyle, player.bowlingStyle, createdAt, updatedAt
        )

        return findById(id) ?: throw RuntimeException("Failed to retrieve saved player.")
    }


    fun findAll(): List<Player> {
        val sql = "SELECT * FROM players"
        return jdbcTemplate.query(sql) { rs, _ ->
            Player(
                id = rs.getString("id"),
                name = rs.getString("name"),
                country = rs.getString("country"),
                age = rs.getInt("age"),
                role = rs.getString("role"),
                battingStyle = rs.getString("batting_style"),
                bowlingStyle = rs.getString("bowling_style"),
                createdAt = rs.getLong("created_at"),
                updatedAt = rs.getLong("updated_at")
            )
        }
    }

    fun findById(id: String): Player? {
        val sql = "SELECT * FROM players WHERE id = ?"
        return jdbcTemplate.query(sql, arrayOf(id)) { rs, _ ->
            Player(
                id = rs.getString("id"),
                name = rs.getString("name"),
                country = rs.getString("country"),
                age = rs.getInt("age"),
                role = rs.getString("role"),
                battingStyle = rs.getString("batting_style"),
                bowlingStyle = rs.getString("bowling_style"),
                createdAt = rs.getLong("created_at"),
                updatedAt = rs.getLong("updated_at")
            )
        }.firstOrNull()
    }

    fun deleteById(id: Int): Int {
        val sql = "DELETE FROM players WHERE id = ?"
        return jdbcTemplate.update(sql, id)
    }
}