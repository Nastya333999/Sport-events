package my.sport.events.domain.usecase

import my.sport.events.domain.model.SportModel
import my.sport.events.domain.repository.SportRepository
import javax.inject.Inject

class GetSportsUseCase @Inject constructor(
    private val updateEventTime: UpdateEventTimeUseCase,
    private val sportRepository: SportRepository
) {
    suspend operator fun invoke(): List<SportModel> {
        val sports = sportRepository.getAll()
        return updateEventTime(sports)
    }

}