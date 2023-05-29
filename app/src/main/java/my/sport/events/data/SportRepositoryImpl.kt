package my.sport.events.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import my.sport.events.data.remote.SportApi
import my.sport.events.data.remote.SportDTO
import my.sport.events.data.remote.mapToDomain
import my.sport.events.domain.model.EventModel
import my.sport.events.domain.model.SportModel
import my.sport.events.domain.model.SportType
import my.sport.events.domain.repository.SportRepository
import java.util.*

class SportRepositoryImpl(private val api: SportApi) : SportRepository {

    override suspend fun getAll(): List<SportModel> = withContext(Dispatchers.IO) {
        api.getAll()
            .map { it.mapToDomain() }
            .distinctBy { it.type }
    }

}