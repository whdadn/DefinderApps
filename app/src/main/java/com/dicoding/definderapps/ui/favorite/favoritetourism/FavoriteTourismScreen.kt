package com.dicoding.definderapps.ui.favorite.favoritetourism

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dicoding.definderapps.R
import com.dicoding.definderapps.ViewModelFactory
import com.dicoding.definderapps.ui.component.home.DestinationItem
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FavoriteTourismScreen(
    modifier: Modifier = Modifier,
    viewModel: FavoriteViewModel = viewModel(factory = ViewModelFactory.getInstance(LocalContext.current)),
    navigateToDetail:(Int)->Unit
) {

    val favoriteDestination by viewModel.getFavoriteDestinationWithImage.collectAsState()


    Box(modifier = modifier) {
        val scope = rememberCoroutineScope()
        val listState = rememberLazyListState()
        val showButton: Boolean by remember {
            derivedStateOf { listState.firstVisibleItemIndex > 0 }
        }
        LazyColumn(
            state = listState,
            contentPadding = PaddingValues(bottom = 10.dp)
        ) {

            item {
                Column{
                    Text(
                        text = stringResource(R.string.favorited_tourism),
                        color = Color(0xFF000080),
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontWeight = FontWeight.Bold,
                            fontStyle = FontStyle.Normal
                        ),
                        modifier = Modifier
                            .padding(top = 10.dp),
                    )
                }
            }

            items(favoriteDestination, key = {it.destination.id}) { data ->
                Column(
                    modifier = Modifier.padding(vertical = 6.dp)
                ) {
                    DestinationItem(
                        name = data.destination.name,
                        imageUrl = data.imageDestination.first().imageUrl,
                        location = data.destination.location,
                        rating = data.destination.rating,
                        favorite = data.destination.favorited,
                        favoriteChange = {
                            if (data.destination.favorited) {
                                viewModel.setFavorited(data.destination.id, false)
                            } else {
                                viewModel.setFavorited(data.destination.id, true)
                            }
                        },
                        modifier = Modifier
                            .animateItemPlacement(tween(durationMillis = 100))
                            .clickable {
                                navigateToDetail(data.destination.id)
                            }
                    )
                }
            }
        }
        AnimatedVisibility(
            visible = showButton,
            enter = fadeIn() + slideInVertically(),
            exit = fadeOut() + slideOutVertically(),
            modifier = Modifier
                .padding(bottom = 20.dp, end = 20.dp)
                .align(Alignment.BottomEnd)
        ) {
            ScrollToTopButton(
                onClick = {
                    scope.launch {
                        listState.scrollToItem(index = 0)
                    }
                }
            )
        }
    }

}

@Composable
fun ScrollToTopButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    FilledIconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            imageVector = Icons.Filled.KeyboardArrowUp,
            contentDescription = stringResource(R.string.scroll_to_top),
        )
    }
}