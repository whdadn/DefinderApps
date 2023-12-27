package com.dicoding.definderapps.ui.favorite.favoritetourguide

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
import com.dicoding.definderapps.R

@Composable
fun FavoriteTourGuideScreen()
{
    Box(Modifier.fillMaxSize()) {
        Column(Modifier.align(Alignment.Center)) {
            Text(
                text = stringResource(R.string.coming_soon),
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Normal,
                    fontStyle = FontStyle.Italic
                ),
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }

//        val listState = rememberLazyListState()
//
//        LazyColumn(
//            state = listState,
//            contentPadding = PaddingValues(bottom = 10.dp)
//        ){
//            item {
//                Column{
//                    Text(
//                        text = "Favorited Tour Guide",
//                        color = MaterialTheme.colorScheme.onSurface,
//                        style = MaterialTheme.typography.headlineMedium.copy(
//                            fontWeight = FontWeight.Bold,
//                            fontStyle = FontStyle.Normal
//                        )
//                    )
//                }
//            }
//
//            items(data){ list ->
//                Column{
//                    TourGuideItem(
//                        imageUrl = list.imageUrl,
//                        name = list.name,
//                        rating = list.rating,
//                        favorite = list.favorite,
//                        favoriteChange = {},
//                    )
//                }
//            }
//        }
}