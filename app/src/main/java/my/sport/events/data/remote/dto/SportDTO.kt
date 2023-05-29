package my.sport.events.data.remote

import com.google.gson.annotations.SerializedName
import my.sport.events.data.remote.dto.EventDTO
import my.sport.events.data.remote.dto.mapSportNames
import my.sport.events.data.remote.dto.mapToDomain
import my.sport.events.domain.model.SportModel

fun SportDTO.mapToDomain(): SportModel {
    return SportModel(
        type = mapSportNames(this.sportId),
        name = this.sportName,
        events = this.events.map { it.mapToDomain() }
    )
}

data class SportDTO(

    @SerializedName("i")
    val sportId: String,

    @SerializedName("d")
    val sportName: String,

    @SerializedName("e")
    val events: List<EventDTO>
)


