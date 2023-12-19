package com.dicoding.definderapps.ui.favorite

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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.dicoding.definderapps.ui.favorite.favoritetourguide.FavoriteTourGuideScreen
import com.dicoding.definderapps.ui.favorite.favoritetourism.FavoriteTourismScreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FavoriteScreen(
    navigateToDetail:(Int)->Unit
)
{
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
                    color = Color(0xFF000066),
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
                selectedContentColor = Color(0xFF000066),
                unselectedContentColor = Color(0xFF79747E),
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
                selectedContentColor = Color(0xFF000066),
                unselectedContentColor = Color(0xFF79747E),
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
                0 -> FavoriteTourismScreen(navigateToDetail = { id ->
                    navigateToDetail(id)
                })
                1 -> FavoriteTourGuideScreen()
            }
        }
    }
}