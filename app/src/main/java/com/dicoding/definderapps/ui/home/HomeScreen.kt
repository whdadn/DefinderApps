package com.dicoding.definderapps.ui.home

import android.content.Context
import android.util.Log
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dicoding.definderapps.R
import com.dicoding.definderapps.ViewModelFactory
import com.dicoding.definderapps.ui.common.ResultState
import com.dicoding.definderapps.ui.component.home.DestinationItem
import com.yogi.foodlist.ui.common.UiState
import kotlinx.coroutines.launch


@Composable
fun HomeScreen(
    context:Context = LocalContext.current,
    viewModel: HomeViewModel = viewModel(factory = ViewModelFactory.getInstance(context)),
    navigateToDetail: (Int) -> Unit
) {
    viewModel.token.collectAsState(initial = UiState.Loading).value.let {
        when (it) {
            is UiState.Loading -> {
                viewModel.getToken()
            }
            is UiState.Success -> {
                GetHomeLoc(
                    context= context,
                    token = it.data.token,
                    navigateToDetail = navigateToDetail,
                    viewModel = viewModel
                )
            }

            is UiState.Error -> {
                Toast.makeText(context, it.errorMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }
}

@Composable
fun GetHomeLoc(
    context: Context,
    token: String,
    navigateToDetail: (Int) -> Unit,
    viewModel: HomeViewModel,
) {
    viewModel.homeLocUiState.collectAsState(initial = UiState.Loading).value.let {
        when (it) {
            is UiState.Loading -> {
                viewModel.getHomeLocPref()
            }

            is UiState.Success -> {
                HomeGetDataLoc(
                    context= context,
                    navigateToDetail = navigateToDetail,
                    homeTitle = if (it.data.name=="" && it.data.province==""){
                        context.getString(R.string.home_title_default)
                    }else{
                        "${it.data.name} Tourism in ${it.data.province}"
                    },
                    token = token,
                    nameDestination = it.data.name,
                    districtDestination = it.data.province,
                    viewModel = viewModel,
                )
            }

            is UiState.Error -> {
                Toast.makeText(context, it.errorMessage, Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeGetDataLoc(
    context:Context,
    navigateToDetail: (Int) -> Unit,
    homeTitle: String,
    token: String,
    nameDestination: String,
    districtDestination: String,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel
) {

    var showLoading by rememberSaveable {
        mutableStateOf(false)
    }
    val scope = rememberCoroutineScope()
    val listState = rememberLazyListState()
    val showButton: Boolean by remember {
        derivedStateOf { listState.firstVisibleItemIndex > 0 }
    }
    Box(modifier = Modifier.fillMaxSize()) {
        if (showLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
        viewModel.dataLocResult.collectAsState(initial = ResultState.Loading).value.let {
            when (it) {
                is ResultState.Loading -> {
                    showLoading = true
                    if (nameDestination=="" && districtDestination==""){
                        viewModel.getPlace(token)
                    }else {
                        viewModel.getPlaceByNameAndDistrict(token, nameDestination, districtDestination)
                    }
                }

                is ResultState.Success -> {
                    Box(modifier = modifier) {
                        LazyColumn(
                            state = listState,
                            contentPadding = PaddingValues(bottom = 10.dp),
                        ) {

                            item {
                                Column(
                                    modifier = Modifier.padding(horizontal = 16.dp)
                                ) {
                                    Text(
                                        text = homeTitle,
                                        color = MaterialTheme.colorScheme.onSurface,
                                        style = MaterialTheme.typography.headlineMedium.copy(
                                            fontWeight = FontWeight.Bold,
                                            fontStyle = FontStyle.Normal
                                        ),
                                        modifier = Modifier
                                            .padding(top = 16.dp),
                                    )
                                }
                            }
                            if (!it.data.data.isNullOrEmpty()) {
                                showLoading = false
                                items(it.data.data, key = { it.id.toString().trim().toInt() }) { data ->
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
//                            if (data.destination.favorited) {
//                                viewModel.setFavorited(data.destination.id, false)
//                            } else {
//                                viewModel.setFavorited(data.destination.id, true)
//                            }
                                            },
                                            modifier = Modifier
                                                .animateItemPlacement(tween(durationMillis = 100))
                                                .clickable {
                                                    navigateToDetail(
                                                        data.id
                                                            .toString()
                                                            .trim()
                                                            .toInt()
                                                    )
                                                }
                                        )
                                    }
                                }
                            }else{
                                item{
                                    showLoading=false
                                    Box(modifier = Modifier.fillMaxSize()){
                                        Column(Modifier.fillMaxSize()) {
                                            Text(text = stringResource(R.string.data_not_found),
                                                style = MaterialTheme.typography.bodyMedium.copy(
                                                    fontWeight = FontWeight.Normal,
                                                    fontStyle = FontStyle.Italic
                                                ))
                                        }
                                    }
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

                is ResultState.Error -> {
                    showLoading = false
                    Toast.makeText(context, it.error, Toast.LENGTH_SHORT).show()
                }
            }
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
    HomeScreen(navigateToDetail = {})
}