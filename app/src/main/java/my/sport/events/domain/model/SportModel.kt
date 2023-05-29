package my.sport.events.domain.model

data class SportModel(
    val type: SportType,
    val name: String,
    val events: List<EventModel>
)