package com.dicoding.definderapps.ui.search

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dicoding.definderapps.ViewModelFactory
import com.dicoding.definderapps.ui.component.home.DestinationItem
import com.dicoding.definderapps.ui.component.home.SearchBar
import com.dicoding.definderapps.ui.home.HomeViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SearchScreen(
    viewModel: HomeViewModel = viewModel(factory = ViewModelFactory.getInstance(LocalContext.current))
) {
    val destinationData by viewModel.getDestination.collectAsState()
    val query by viewModel.query

    Box(modifier = Modifier) {
        val listState = rememberLazyListState()

        LazyColumn(
            state = listState,
            contentPadding = PaddingValues(bottom = 10.dp)
        ) {
            stickyHeader {
                Column(
                    modifier = Modifier.background(MaterialTheme.colorScheme.background)
                ) {
                    SearchBar(
                        query = query,
                        onQueryChange = viewModel::search,
                        modifier = Modifier
                            .padding(vertical = 16.dp, horizontal = 16.dp)
                    )
                }
            }


            if (query.isNotEmpty()) {
                items(destinationData, key = { it.id }) { destination ->
                    Column(
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp)
                    ) {
                        DestinationItem(
                            name = destination.name,
                            imageUrl = destination.imageUrl,
                            location = destination.location,
                            rating = destination.rating,
                            favorite = destination.favorited,
                            favoriteChange = {
                                if (destination.favorited) {
                                    viewModel.setFavorited(destination.id, false)
                                } else {
                                    viewModel.setFavorited(destination.id, true)
                                }
                            },
                            modifier = Modifier
                                .animateItemPlacement(tween(durationMillis = 100))
                                .clickable { }
                        )
                    }
                }
            }
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
fun SearchScreenPreview() {
    SearchScreen()
}