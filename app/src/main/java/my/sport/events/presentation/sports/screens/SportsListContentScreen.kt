package my.sport.events.presentation.sports.screens

import androidx.compose.animation.*
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import my.sport.events.R
import my.sport.events.domain.model.EventModel
import my.sport.events.domain.model.SportModel
import my.sport.events.domain.model.SportType
import my.sport.events.presentation.sports.SportScreenState


@Composable
fun SportsListContentScreen(
    state: SportScreenState.ContentState,
    onEventClicked: (EventModel) -> Unit
) {
    SportsList(
        sportList = state.items,
        onEventClicked = onEventClicked
    )
}

// Список спортов вертикальный
@Composable
fun SportsList(
    sportList: List<SportModel>,
    onEventClicked: (EventModel) -> Unit
) {
    // Список спортов вертикальный
    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {

        items(sportList) { item ->
            SportItemElement(
                sportItem = item,
                onEventClicked = {
                    onEventClicked(it)
                }
            )
        }
    }
}

@Composable
fun SportItemElement(
    sportItem: SportModel,
    onEventClicked: (EventModel) -> Unit
) {
   val sportIcon = when(sportItem.type){
       SportType.FOOTBALL -> R.drawable.ic_football
       SportType.BASKETBALL -> R.drawable.ic_basketball
       SportType.TENNIS -> R.drawable.ic_tennis
       SportType.TABLLE_TENNIS -> R.drawable.ic_table_tennis
       SportType.ESPORTS -> R.drawable.ic_esport
       SportType.HANDBALL -> R.drawable.ic_handball
       SportType.BEACH_VOLLEYBALL -> R.drawable.ic_beach_volleyball
       SportType.NONE -> R.drawable.ic_game_standart
   } 
    

    var isExpand by remember { mutableStateOf(true) }
    val angle: Float by animateFloatAsState(if (isExpand) 360f else 180f)

    Column() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { isExpand = !isExpand }
                .background(colorResource(id = R.color.title_line_color)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painterResource(sportIcon),
                contentDescription = "sport",
                modifier = Modifier
                    .width(32.dp)
                    .height(32.dp)
                    .padding(start = 8.dp, end = 4.dp),
                colorFilter = ColorFilter.tint(Color.White)
            )
            Text(
                text = sportItem.name,
                color = Color.White,
                modifier = Modifier
                    .padding(8.dp)
                    .weight(1f),
                fontWeight = FontWeight.Bold
            )
            Image(
                painterResource(id = R.drawable.icon_arrow),
                contentDescription = "arrow",
                modifier = Modifier
                    .width(24.dp)
                    .height(24.dp)
                    .rotate(angle)
                    .padding(end = 8.dp)
            )
        }

        ExpandableView(isExpand, events = sportItem.events, onEventClicked = onEventClicked)
    }
}

@Composable
fun ExpandableView(
    isExpanded: Boolean,
    events: List<EventModel>,
    onEventClicked: (EventModel) -> Unit
) {

    val expandTransition = remember {
        expandVertically(
            expandFrom = Alignment.Top,
            animationSpec = tween(300)
        ) + fadeIn(
            animationSpec = tween(300)
        )
    }
    val collapseTransition = remember {
        shrinkVertically(
            shrinkTowards = Alignment.Top,
            animationSpec = tween(300)
        ) + fadeOut(
            animationSpec = tween(300)
        )
    }
    AnimatedVisibility(
        visible = isExpanded,
        enter = expandTransition,
        exit = collapseTransition
    ) {
        ListEvent(events, onEventClicked)
    }

}

// Список спортов горизонтальный
@Composable
fun ListEvent(eventsList: List<EventModel>, onEventClicked: (EventModel) -> Unit) {
    LazyRow(
        modifier = Modifier.fillMaxWidth()
    ) {
        items(eventsList) { event ->
            CardEvent(
                event = event,
                onEventClicked = {
                    onEventClicked(event)
                }
            )
        }
    }
}

// Карточка события
@Composable
fun CardEvent(event: EventModel, onEventClicked: () -> Unit) {
    val starDrawable = if (event.isLiked) {
        R.drawable.star_checked
    } else {
        R.drawable.star
    }
    val colorFilter: ColorFilter? = if (event.isLiked) {
        null
    } else {
        ColorFilter.tint(MaterialTheme.colors.secondary)
    }

    Column(
        modifier = Modifier
            .width(120.dp)
            .clickable { onEventClicked() }
            .padding(horizontal = 4.dp, vertical = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = event.sportDisplayTime,
            modifier = Modifier
                .width(100.dp)
                .border(
                    BorderStroke(1.dp, Color.White),
                    shape = RoundedCornerShape(4.dp)
                )
                .align(Alignment.CenterHorizontally)
                .padding(2.dp),
            fontSize = 15.sp,
            textAlign = TextAlign.Center
        )

        Image(
            painterResource(id = starDrawable), contentDescription = "star",
            modifier = Modifier
                .width(40.dp)
                .height(40.dp)
                .padding(8.dp),
            colorFilter = colorFilter,
            alignment = Alignment.Center
        )
        Text(
            text = event.name,
            modifier = Modifier.width(120.dp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontSize = 15.sp,
        )
        Text(
            text = event.subName,
            modifier = Modifier.width(120.dp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontSize = 15.sp,
        )
    }
}