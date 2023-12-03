package com.dicoding.definderapps.ui.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dicoding.definderapps.R
import com.dicoding.definderapps.ViewModelFactory
import com.dicoding.definderapps.di.Injection
import com.dicoding.definderapps.ui.component.home.DestinationItem
import com.dicoding.definderapps.ui.component.home.SearchBar
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    )
) {
    var favoriteState by rememberSaveable { mutableStateOf(false) }

    val destinationData by viewModel.getDestination.collectAsState()
    val query by viewModel.query



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
            stickyHeader {
                Column(
                    modifier=Modifier.background(MaterialTheme.colorScheme.background)
                ) {
                    SearchBar(
                        query = query,
                        onQueryChange = viewModel::search,
                        modifier = Modifier
                            .padding(vertical = 16.dp, horizontal = 16.dp)
                    )
                }
            }


            if (query.isEmpty()) {
                item {
                    Column(
                        modifier = Modifier.padding(horizontal = 16.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.info_home),
                            color = Color(0xFF000080),
                            style = MaterialTheme.typography.headlineMedium.copy(
                                fontWeight = FontWeight.Bold,
                                fontStyle = FontStyle.Normal
                            )
                        )
                    }
                }
            }

            items(destinationData, key = { it.id }) { destination ->
                Column(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp)
                ) {
                    DestinationItem(
                        name = destination.name,
                        imageUrl = destination.imageUrl,
                        location = destination.location,
                        rating = destination.rating,
                        favorite = favoriteState,
                        favoriteChange = { favoriteState = it },
                        modifier = Modifier
                            .animateItemPlacement(tween(durationMillis = 100))
                            .clickable { }
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


@Preview(showBackground = true, device = Devices.PIXEL_4_XL)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}