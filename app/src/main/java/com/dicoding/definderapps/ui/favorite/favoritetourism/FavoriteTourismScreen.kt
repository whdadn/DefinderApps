@file:Suppress("NAME_SHADOWING")

package com.dicoding.definderapps.ui.favorite.favoritetourism

import android.content.Context
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dicoding.definderapps.R
import com.dicoding.definderapps.ViewModelFactory
import com.dicoding.definderapps.ui.common.ResultState
import com.dicoding.definderapps.ui.component.home.DestinationItem
import com.dicoding.definderapps.ui.home.ScrollToTopButton
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FavoriteTourismScreen(
    modifier: Modifier = Modifier,
    token: String,
    context: Context = LocalContext.current,
    viewModel: FavoriteViewModel = viewModel(factory = ViewModelFactory.getInstance(context)),
    navigateToDetail: (Int) -> Unit,
) {
    var showLoading by rememberSaveable { mutableStateOf(false) }
    Box(modifier = modifier.fillMaxSize()) {
        if (showLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
        viewModel.dataFavTourism.collectAsState(initial = ResultState.Loading).value.let { fav->
            when (fav) {
                is ResultState.Loading -> {
                    showLoading = true
                    viewModel.getFavTourism(token)
                }

                is ResultState.Success -> {
                    val getAllPlace by viewModel.getAllFavPlace().observeAsState()
                    showLoading = false

                    val scope = rememberCoroutineScope()
                    val listState = rememberLazyListState()
                    val showButton: Boolean by remember {
                        derivedStateOf { listState.firstVisibleItemIndex > 0 }
                    }
                    if (!fav.data.data.isNullOrEmpty()){
                        LazyColumn(
                            state = listState,
                            contentPadding = PaddingValues(bottom = 10.dp)
                        ) {
                            item {
                                Column {
                                    Text(
                                        text = stringResource(R.string.favorited_tourism),
                                        color = MaterialTheme.colorScheme.onSurface,
                                        style = MaterialTheme.typography.headlineMedium.copy(
                                            fontWeight = FontWeight.Bold,
                                            fontStyle = FontStyle.Normal
                                        ),
                                        modifier = Modifier
                                            .padding(top = 10.dp),
                                    )
                                }
                            }
                            items(fav.data.data, key = { it?.favoriteId.toString().toInt() }) { data ->
                                Column(modifier = Modifier.padding(vertical = 6.dp)) {
                                    if (getAllPlace.isNullOrEmpty()){
                                        viewModel.insertFavPlace(data?.placeId.toString().toInt())
                                    }
                                    val fav by viewModel.getFavById(data?.placeId.toString().toInt()).observeAsState()
                                    val idPlace = fav?.placeId
                                    DestinationItem(
                                        name = data?.name.toString(),
                                        imageUrl = data?.image.toString(),
                                        location = data?.location.toString(),
                                        rating = data?.rating.toString(),
                                        review = data?.reviews.toString().toInt(),
                                        favorite = idPlace == data?.placeId.toString().toInt(),
                                        favoriteChange = {
                                            if (idPlace == data?.placeId.toString().toInt()){
                                                scope.launch {
                                                    viewModel.deleteFavPlaceApi(token, data?.placeId.toString().toInt()).asFlow().collect{
                                                        when(it){
                                                            is ResultState.Loading->{
                                                                showLoading=true
                                                            }
                                                            is ResultState.Success->{
                                                                val delete = viewModel.deleteFavPlace(data?.placeId.toString().toInt())
                                                                if (delete){
                                                                    viewModel.getFavTourism(token)
                                                                    showLoading=false
                                                                }
                                                                Toast.makeText(context, "${data?.name.toString()} deleted from favorites", Toast.LENGTH_SHORT).show()
                                                            }
                                                            is ResultState.Error->{}
                                                        }
                                                    }
                                                }
                                            }else{
                                                viewModel.insertFavPlace(data?.placeId.toString().toInt())
                                                scope.launch {
                                                    viewModel.insertFavPlaceApi(token,data?.placeId.toString().toInt()).asFlow().collect{
                                                        when(it){
                                                            is ResultState.Loading->{}
                                                            is ResultState.Success->{
                                                                Toast.makeText(context, "${data?.name.toString()} added to favorites", Toast.LENGTH_SHORT).show()
                                                            }
                                                            is ResultState.Error->{}
                                                        }
                                                    }
                                                }
                                            }
                                        },
                                        modifier = Modifier
                                            .animateItemPlacement(tween(durationMillis = 100))
                                            .clickable {
                                                navigateToDetail(
                                                    data?.placeId
                                                        .toString()
                                                        .toInt()
                                                )
                                            }
                                    )
                                }
                            }
                        }
                    }else{
                        Column(Modifier.align(Alignment.Center)) {
                            Text(text = stringResource(R.string.you_havent_added_favorite_place),
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    fontWeight = FontWeight.Normal,
                                    fontStyle = FontStyle.Italic
                                ))
                        }
                    }

                    AnimatedVisibility(
                        visible = showButton,
                        enter = fadeIn() + slideInVertically(),
                        exit = fadeOut() + slideOutVertically(),
                        modifier = Modifier
                            .padding(bottom = 16.dp, end = 16.dp)
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

                is ResultState.Error -> {
                    showLoading = false
                    Toast.makeText(context, fav.error, Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

}