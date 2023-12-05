package com.dicoding.definderapps.ui.component.detail

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dicoding.definderapps.R
import com.dicoding.definderapps.ui.component.detail.about.AboutPage
import com.dicoding.definderapps.ui.component.detail.amusementrides.AmusementRidesListItems
import com.dicoding.definderapps.ui.component.detail.reviews.ReviewsListItem
import com.dicoding.definderapps.ui.component.detail.transportation.TransportationListItem
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DetailScreenPage() {
    val images = listOf(
        R.drawable.candi_borobudur,
        R.drawable.borobudur2,
        R.drawable.borobudur3
    )
    val pagerState = rememberPagerState { images.size }
    val tabState = rememberPagerState { 4 }
    val coroutinScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .wrapContentSize(),
        ) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .wrapContentSize()
            ) { currentPage ->
                Card(
                    modifier = Modifier
                        .padding(end = 7.dp)
                        .wrapContentSize(),
                    elevation = CardDefaults.cardElevation(8.dp)
                ) {
                    Image(
                        painter = painterResource(id = images[currentPage]),
                        contentDescription = "image_slider",
                    )
                }
            }
        }
        PageIndex(
            pageCount = images.size,
            currentPage = pagerState.currentPage,
            modifier = Modifier
                .padding(top = 7.dp)
        )

        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier
                    .padding(top = 10.dp),
                text = "Candi Borobudur",
                color = Color(0xFF000080),
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Normal
                ),
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                painter = painterResource(id = R.drawable.ticket_fill),
                contentDescription = "ticket_logo",
                modifier = Modifier
                    .padding(top = 12.dp)
                    .size(28.dp),
                tint = Color(0xFF000080)
            )
            Text(
                modifier = Modifier
                    .padding(start = 6.dp, top = 10.dp, end = 7.dp),
                text = "$5",
                color = Color(0xFF000080),
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Normal
                ),
            )
        }
        ScrollableTabRow(
            selectedTabIndex = tabState.currentPage,
            divider = {},
            indicator = {tabPositions ->
                TabRowDefaults.Indicator(
                    modifier = Modifier
                        .tabIndicatorOffset(tabPositions[tabState.currentPage])
                        .height(2.dp),
                    color = Color(0xFF000066),
                )
            },
            edgePadding = 0.dp
        ) {
            Tab(
                selected = tabState.currentPage == 0,
                text ={
                    Text(
                        text = "About",
                        style = MaterialTheme.typography.labelMedium.copy(
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
                        text = "Transport",
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontWeight = FontWeight.Normal,
                            fontStyle = FontStyle.Normal
                        )
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
            Tab(
                selected = tabState.currentPage == 2,
                text ={
                    Text(
                        text = "Amusement Rides",
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Normal,
                            fontStyle = FontStyle.Normal,
                        ),
                    )
                },
                selectedContentColor = Color(0xFF000066),
                unselectedContentColor = Color(0xFF79747E),
                onClick = {
                    coroutinScope.launch {
                        tabState.animateScrollToPage(2)
                    }
                }
            )
            Tab(
                selected = tabState.currentPage == 3,
                text ={
                    Text(
                        text = "Reviews",
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontWeight = FontWeight.Normal,
                            fontStyle = FontStyle.Normal
                        )
                    )
                },
                selectedContentColor = Color(0xFF000066),
                unselectedContentColor = Color(0xFF79747E),
                onClick = {
                    coroutinScope.launch {
                        tabState.animateScrollToPage(3)
                    }
                }
            )
        }

        HorizontalPager(
            state = tabState,
        ) {page ->
            when(page){
                0 -> AboutPage()
                1 -> TransportationListItem()
                2 -> AmusementRidesListItems()
                3 -> ReviewsListItem()
            }
        }
    }
}

@Composable
fun PageIndex(pageCount: Int, currentPage: Int, modifier: Modifier) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        repeat(pageCount)
        {
            IndicatorDots(isSelected = it == currentPage)
        }
    }
}

@Composable
fun IndicatorDots(isSelected: Boolean) {
    Box(
        modifier = Modifier
            .padding(start = 2.dp, end = 2.dp)
            .size(8.dp)
            .clip(CircleShape)
            .background(if (isSelected) Color(0xFF000080) else Color(0xFFD9D9EC)),
    ) {

    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    device = Devices.PIXEL_4_XL
)
@Composable
fun DetailScreenPreview() {
    DetailScreenPage()
}