package my.sport.events.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.runtime.*
import dagger.hilt.android.AndroidEntryPoint
import my.sport.events.presentation.sports.SportScreenState
import my.sport.events.presentation.sports.SportsViewModel
import my.sport.events.presentation.sports.screens.SportsListLoadingState
import my.sport.events.presentation.sports.screens.SportsListEmptyState
import my.sport.events.presentation.sports.screens.SportsListErrorState
import my.sport.events.presentation.sports.screens.SportsListContentScreen
import my.sport.events.presentation.theme.SportEventsViewerTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: SportsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SportEventsViewerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val state = viewModel.state.collectAsState().value
                    when (state) {
                        is SportScreenState.LoadingState -> RenderLoadingState(state = state)
                        is SportScreenState.ContentState -> RenderContentState(state = state)
                        is SportScreenState.EmptyState -> RenderEmptyState(state = state)
                        is SportScreenState.ErrorState -> RenderErrorState(state = state)
                    }
                }
            }
        }
    }

    @Composable
    fun RenderContentState(state: SportScreenState.ContentState) {
        SportsListContentScreen(
            state = state,
            onEventClicked = { viewModel.onEventClicked(it) },
        )
    }

    @Composable
    fun RenderErrorState(state: SportScreenState.ErrorState) {
        SportsListErrorState(
            state = state,
            retryClick = { viewModel.retryClick() }
        )
    }

    @Composable
    fun RenderEmptyState(state: SportScreenState.EmptyState) {
        SportsListEmptyState(state = state)
    }

    @Composable
    fun RenderLoadingState(state: SportScreenState.LoadingState) {
        SportsListLoadingState(state = state)
    }


}


// 1. Цвет фона и вообще цвета
// 2. картинки в зависимости от спорта
// 3. таймер
// 4. ретрофит
// 5. звездочка
// 6. перенос текста название матча
// 7. маппер
// 8. вью модель через фектори
// 9. ориентация почитать в ТЗ
//10. Icon btn start
