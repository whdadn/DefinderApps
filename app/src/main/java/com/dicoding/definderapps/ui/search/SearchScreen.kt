package com.dicoding.definderapps.ui.search

import android.widget.Toast
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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dicoding.definderapps.ViewModelFactory
import com.dicoding.definderapps.ui.common.ResultState
import com.dicoding.definderapps.ui.component.home.DestinationItem
import com.dicoding.definderapps.ui.component.search.SearchBar
import com.dicoding.definderapps.ui.home.ScrollToTopButton
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SearchScreen(
    navigateToDetail:(Int)->Unit,
    modifier:Modifier = Modifier,
    viewModel: SearchViewModel = viewModel(factory = ViewModelFactory.getInstance(LocalContext.current))
) {
    val query by viewModel.query
    var showLoading by rememberSaveable { mutableStateOf(false) }
    Box(modifier = Modifier.fillMaxSize()) {
        if (showLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
        val scope = rememberCoroutineScope()
        val listState = rememberLazyListState()
        val showButton: Boolean by remember {
            derivedStateOf { listState.firstVisibleItemIndex > 0 }
        }
        Column(
        ) {
            viewModel.dataLocResult.collectAsState(initial = ResultState.Loading).value.let {
                when (it) {
                    is ResultState.Loading -> {
                        showLoading=true
                        viewModel.getPlaceByName(query)
                    }

                    is ResultState.Success -> {
                        showLoading=false
                        SearchBar(
                            query = if (query=="null1") "" else query,
                            onQueryChange = viewModel::getPlaceByName,
                            modifier = Modifier
                                .padding(vertical = 16.dp, horizontal = 16.dp)
                        )
                        Box(modifier = modifier) {
                            LazyColumn(
                                state = listState,
                                contentPadding = PaddingValues(bottom = 10.dp)
                            ) {
                                if (query.isNotEmpty()) {
                                    if (!it.data.data.isNullOrEmpty()) {
                                        items(
                                            it.data.data,
                                            key = { it.id.toString().trim().toInt() }) { data ->
                                            Column(
                                                modifier = Modifier.padding(
                                                    horizontal = 16.dp,
                                                    vertical = 6.dp
                                                )
                                            ) {
                                                DestinationItem(
                                                    name = data.name.toString(),
                                                    imageUrl = data.image.toString(),
                                                    location = data.location.toString(),
                                                    rating = data.rating.toString(),
                                                    review = data.reviews.toString().trim().toInt(),
                                                    favorite = false,
                                                    favoriteChange = {
//                                            if (data.destination.favorited) {
//                                                viewModel.setFavorited(data.destination.id, false)
//                                            } else {
//                                                viewModel.setFavorited(data.destination.id, true)
//                                            }
                                                    },
                                                    modifier = Modifier
                                                        .animateItemPlacement(tween(durationMillis = 100))
                                                        .clickable {
                                                            navigateToDetail(data.id.toString().trim().toInt())
                                                        }
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                            this@Column.AnimatedVisibility(
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

                    is ResultState.Error -> {
                        showLoading=false
                        Toast.makeText(LocalContext.current, it.error, Toast.LENGTH_SHORT).show()

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
    SearchScreen(navigateToDetail = {})
}