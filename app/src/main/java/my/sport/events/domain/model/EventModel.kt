package my.sport.events.domain.model

import java.util.*

data class EventModel(
    val name: String,
    val subName: String,
    val id: String,
    val sportId: SportType,
    val startTime: Date,
    val isLiked: Boolean,
    val sportDisplayTime: String
)
