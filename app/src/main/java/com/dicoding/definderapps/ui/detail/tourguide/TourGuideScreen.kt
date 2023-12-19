package com.dicoding.definderapps.ui.detail.tourguide

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
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
        false
    ),
    TourGuide(
        2,
        R.drawable.borobudur3,
        "Geto",
        "4.5 (220)",
        false
    ),
    TourGuide(
        3,
        R.drawable.candi_sewu,
        "Nobara",
        "4.5 (220)",
        false
    ),
    TourGuide(
        4,
        R.drawable.candi_kalasan,
        "Megumi",
        "4.5 (220)",
        false
    ),
    TourGuide(
        5,
        R.drawable.candi_dieng,
        "Itadori",
        "4.5 (220)",
        false
    ),
)

@Composable
fun TourGuideScreen() {
    Box {
        val listState = rememberLazyListState()

        LazyColumn(
            state = listState,
        ){
            items(data){list ->
                TourGuideItem(
                    imageUrl = list.imageUrl,
                    name = list.name,
                    rating = list.rating,
                    favorite = list.favorite,
                    favoriteChange = {}
                )
            }
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
fun TourGuideScreenPreview()
{
    TourGuideScreen()
}