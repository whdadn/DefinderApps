package com.dicoding.definderapps.ui.favorite.favoritetourguide

import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.dicoding.definderapps.R
import com.dicoding.definderapps.ui.component.detail.tourguide.TourGuideItem

data class TourGuide(
    val id: Int,
    val imageUrl: Int,
    val name: String,
    val rating: String,
    val favorite: Boolean
)

val data = listOf(
    TourGuide(
        1,
        R.drawable.borobudur2,
        "Sukuna",
        "4.5 (220)",
        true
    ),
    TourGuide(
        2,
        R.drawable.borobudur3,
        "Geto",
        "4.5 (220)",
        true
    ),
    TourGuide(
        3,
        R.drawable.candi_sewu,
        "Nobara",
        "4.5 (220)",
        true
    ),
    TourGuide(
        4,
        R.drawable.candi_kalasan,
        "Megumi",
        "4.5 (220)",
        true
    ),
    TourGuide(
        5,
        R.drawable.candi_dieng,
        "Itadori",
        "4.5 (220)",
        true
    ),
)
@Composable
fun FavoriteTourGuideScreen()
{
    Box {
        val listState = rememberLazyListState()

        LazyColumn(
            state = listState,
            contentPadding = PaddingValues(bottom = 10.dp)
        ){
            item {
                Column{
                    Text(
                        text = "Favorited Tour Guide",
                        color = Color(0xFF000080),
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontWeight = FontWeight.Bold,
                            fontStyle = FontStyle.Normal
                        )
                    )
                }
            }

            items(data){ list ->
                Column{
                    TourGuideItem(
                        imageUrl = list.imageUrl,
                        name = list.name,
                        rating = list.rating,
                        favorite = list.favorite,
                        favoriteChange = {},
                    )
                }
            }
        }
    }
}