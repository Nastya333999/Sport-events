package my.sport.events.domain.repository

import my.sport.events.domain.model.SportModel

interface SportRepository {
    suspend fun getAll(): List<SportModel>
}