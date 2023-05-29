package my.sport.events.domain.usecase

import my.sport.events.domain.model.SportModel
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class UpdateEventTimeUseCase @Inject constructor() {

    suspend operator fun invoke(list: List<SportModel>): List<SportModel> {
        val currentDate = Date()
        return list.map { sport ->
            val updatedEvents = sport.events.map { event ->
                event.copy(
                    sportDisplayTime = formatDate(
                        eventDate = event.startTime,
                        currentDate = currentDate
                    )
                )
            }
            sport.copy(events = updatedEvents)
        }
    }

    private fun formatDate(eventDate: Date, currentDate: Date): String {
        val timeDifference = eventDate.time - currentDate.time
        val hoursDifference = TimeUnit.MILLISECONDS.toHours(timeDifference)
        val minutesDifference = TimeUnit.MILLISECONDS.toMinutes(timeDifference) % 60
        val secondsDifference = TimeUnit.MILLISECONDS.toSeconds(timeDifference) % 60

        return String.format(
            "%02d:%02d:%02d",
            hoursDifference,
            minutesDifference,
            secondsDifference
        )
    }
}