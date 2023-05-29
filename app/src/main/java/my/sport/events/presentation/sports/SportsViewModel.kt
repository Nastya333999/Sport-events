package my.sport.events.presentation.sports

import android.os.CountDownTimer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import my.sport.events.domain.model.EventModel
import my.sport.events.domain.model.SportModel
import my.sport.events.domain.usecase.GetSportsUseCase
import my.sport.events.domain.usecase.UpdateEventTimeUseCase
import javax.inject.Inject

@HiltViewModel
class SportsViewModel @Inject constructor(
    private val getSportsUseCase: GetSportsUseCase,
    private val updateEventTimeUseCase: UpdateEventTimeUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<SportScreenState>(SportScreenState.LoadingState)
    val state: StateFlow<SportScreenState> = _state

    private var timer: CountDownTimer? = null

    init {
        loadSports()
    }

    private fun loadSports() {
        viewModelScope.launch {
            _state.emit(SportScreenState.LoadingState)
            val newState = try {
                val sports: List<SportModel> = getSportsUseCase()
                if (sports.isEmpty()) {
                    SportScreenState.EmptyState
                } else {
                    initTimer()
                    SportScreenState.ContentState(items = sports)
                }
            } catch (e: Throwable) {
                SportScreenState.ErrorState
            }
            _state.emit(newState)
        }
    }

    private fun initTimer() {
        timer?.cancel()
        timer = object : CountDownTimer(Long.MAX_VALUE, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                viewModelScope.launch {
                    val currentState = _state.value
                    if (currentState is SportScreenState.ContentState) {
                        val updatedTimesSportList = updateEventTimeUseCase(currentState.items)
                        _state.emit(
                            currentState.copy(items = updatedTimesSportList)
                        )
                    }
                }
            }

            override fun onFinish() {}
        }
        timer?.start()
    }

    fun onEventClicked(item: EventModel) {
        viewModelScope.launch {
            val currentState = _state.value
            if (currentState is SportScreenState.ContentState) {
                val updatedListOfSports = currentState.items.map { sportModel ->
                    val updatedEvents = sportModel.events
                        .map { event ->
                            if (event == item)
                                event.copy(isLiked = !item.isLiked)
                            else
                                event
                        }
                        .sortedBy { !it.isLiked }
                    sportModel.copy(events = updatedEvents)
                }
                _state.emit(currentState.copy(items = updatedListOfSports))
            }
        }
    }

    fun retryClick() {
        if (_state.value is SportScreenState.ErrorState)
            loadSports()
    }
}