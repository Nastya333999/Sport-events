package my.sport.events.presentation.sports

import my.sport.events.domain.model.SportModel

sealed interface SportScreenState {
    object LoadingState : SportScreenState
    object EmptyState : SportScreenState
    object ErrorState : SportScreenState

    data class ContentState(
        val items: List<SportModel>
    ) : SportScreenState
}
