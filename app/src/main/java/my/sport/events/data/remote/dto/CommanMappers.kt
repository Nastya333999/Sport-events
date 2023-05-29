package my.sport.events.data.remote.dto

import android.util.Log
import my.sport.events.domain.model.SportType
import java.util.*

fun mapSportNames(id: String): SportType {
    return when (id) {
        "FOOT" -> SportType.FOOTBALL
        "BASK" -> SportType.BASKETBALL
        "TENN" -> SportType.TENNIS
        "TABL" -> SportType.TABLLE_TENNIS
        "ESPS" -> SportType.ESPORTS
        "HAND" -> SportType.HANDBALL
        "BCHV" -> SportType.BEACH_VOLLEYBALL
        else -> SportType.NONE
    }
}

fun mapToDate(time: Long): Date {
    return Date(time * 1000)
}