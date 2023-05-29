package my.sport.events.presentation.sports.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import my.sport.events.R
import my.sport.events.presentation.sports.SportScreenState

@Composable
fun SportsListLoadingState(state: SportScreenState.LoadingState,) {
    Box(
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(50.dp)
                        .padding(16.dp)
                )
                Text(
                    text = stringResource(id = R.string.loading_text),
                    modifier = Modifier.padding(top = 8.dp)
                )
        }
    }
}