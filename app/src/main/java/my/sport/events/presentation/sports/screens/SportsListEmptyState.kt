package my.sport.events.presentation.sports.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import my.sport.events.R
import my.sport.events.presentation.sports.SportScreenState

@Composable
fun SportsListEmptyState(state: SportScreenState.EmptyState) {
    Box(
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_empty),
                contentDescription = "empty image"
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = stringResource(id = R.string.empty_text))
        }
    }
}