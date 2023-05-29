package my.sport.events.data.remote.dto

import com.google.gson.annotations.SerializedName
import my.sport.events.domain.model.EventModel

fun EventDTO.mapToDomain(): EventModel {
    val names: List<String> = this.eventName.split(" - ")
    return EventModel(
        name = names.getOrNull(0) ?: "",
        subName = names.getOrNull(1) ?: "",
        id = this.eventId,
        sportId = mapSportNames(this.sportId),
        startTime = mapToDate(this.eventStartTime),
        isLiked = false,
        sportDisplayTime = ""
    )
}

data class EventDTO(

    @SerializedName("i")
    val eventId: String,

    @SerializedName("si")
    val sportId: String,

    @SerializedName("d")
    val eventName: String,

    @SerializedName("tt")
    val eventStartTime: Long
)