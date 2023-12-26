package com.dicoding.definderapps.ui.detail.tourguide

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.dicoding.definderapps.R

//data class TourGuide(
//    val id: Int,
//    val imageUrl: Int,
//    val name: String,
//    val rating: String,
//    val favorite: Boolean
//)


@Composable
fun TourGuideScreen() {
    Box(Modifier.fillMaxSize()) {
        Column(Modifier.align(Alignment.Center)) {
            Text(
                text = stringResource(R.string.coming_soon),
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Normal,
                    fontStyle = FontStyle.Italic
                )
            )
        }


//        val listState = rememberLazyListState()
//
//        LazyColumn(
//            state = listState,
//        ){
//            items(data){list ->
//                TourGuideItem(
//                    imageUrl = list.imageUrl,
//                    name = list.name,
//                    rating = list.rating,
//                    favorite = list.favorite,
//                    favoriteChange = {}
//                )
//            }
//        }
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