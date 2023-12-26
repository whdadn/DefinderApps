package com.dicoding.definderapps.ui.favorite

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dicoding.definderapps.ViewModelFactory
import com.dicoding.definderapps.ui.favorite.favoritetourguide.FavoriteTourGuideScreen
import com.dicoding.definderapps.ui.favorite.favoritetourism.FavoriteTourismScreen
import com.dicoding.definderapps.ui.favorite.favoritetourism.FavoriteViewModel
import com.yogi.foodlist.ui.common.UiState
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FavoriteScreen(
    navigateToDetail:(Int)->Unit,
    viewModel: FavoriteViewModel = viewModel(factory = ViewModelFactory.getInstance(LocalContext.current)),
) {
    viewModel.token.collectAsState(initial = UiState.Loading).value.let {
        when(it){
            is UiState.Loading->{
                viewModel.getToken()
            }
            is UiState.Success->{
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    val tabState = rememberPagerState { 2 }
                    val coroutinScope = rememberCoroutineScope()

                    TabRow(
                        selectedTabIndex = tabState.currentPage,
                        containerColor = TabRowDefaults.containerColor,
                        contentColor = TabRowDefaults.contentColor,
                        indicator = {tabPositions ->
                            TabRowDefaults.Indicator(
                                modifier = Modifier
                                    .tabIndicatorOffset(tabPositions[tabState.currentPage])
                                    .height(2.dp),
                                color = MaterialTheme.colorScheme.secondary,
                            )
                        },
                    ) {
                        Tab(
                            selected = tabState.currentPage == 0,
                            text ={
                                Text(
                                    text = "Tourism",
                                    style = MaterialTheme.typography.titleSmall.copy(
                                        fontWeight = FontWeight.Normal,
                                        fontStyle = FontStyle.Normal
                                    ),
                                )
                            },
                            selectedContentColor = MaterialTheme.colorScheme.secondary,
                            unselectedContentColor = MaterialTheme.colorScheme.outline,
                            onClick = {
                                coroutinScope.launch {
                                    tabState.animateScrollToPage(0)
                                }
                            }
                        )
                        Tab(
                            selected = tabState.currentPage == 1,
                            text ={
                                Text(
                                    text = "Tour Guide",
                                    style = MaterialTheme.typography.titleSmall.copy(
                                        fontWeight = FontWeight.Normal,
                                        fontStyle = FontStyle.Normal
                                    ),
                                )
                            },
                            selectedContentColor = MaterialTheme.colorScheme.secondary,
                            unselectedContentColor = MaterialTheme.colorScheme.outline,
                            onClick = {
                                coroutinScope.launch {
                                    tabState.animateScrollToPage(1)
                                }
                            }
                        )
                    }
                    HorizontalPager(
                        state = tabState,
                    ) {page ->
                        when(page){
                            0 -> {
                                FavoriteTourismScreen(
                                    token= it.data.token,
                                    navigateToDetail = { id ->
                                        navigateToDetail(id)
                                    }
                                )
                            }
                            1 -> FavoriteTourGuideScreen()
                        }
                    }
                }

            }
            is UiState.Error->{
                Toast.makeText(LocalContext.current, it.errorMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }
}