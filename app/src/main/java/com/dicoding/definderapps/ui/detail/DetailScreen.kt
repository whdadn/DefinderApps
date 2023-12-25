package com.dicoding.definderapps.ui.detail

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.dicoding.definderapps.R
import com.dicoding.definderapps.ViewModelFactory
import com.dicoding.definderapps.ui.common.ResultState
import com.dicoding.definderapps.ui.detail.about.AboutScreen
import com.dicoding.definderapps.ui.detail.reviews.ReviewsScreen
import com.dicoding.definderapps.ui.detail.tourguide.TourGuideScreen
import com.dicoding.definderapps.ui.detail.transportation.TransportationScreen
import kotlinx.coroutines.launch

@SuppressLint("DiscouragedApi")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DetailScreen(
    id:Int,
    modifier:Modifier=Modifier,
    context: Context = LocalContext.current,
    viewModel: DetailViewModel =  viewModel(factory = ViewModelFactory.getInstance(LocalContext.current)),
    navigateToDetailTransport:(Int,String)->Unit,
) {
    val pref by viewModel.getSession().observeAsState()
    var showLoading by rememberSaveable { mutableStateOf(false) }
    Box(modifier = modifier.fillMaxSize()){
        if (showLoading){
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
        viewModel.dataDetailPlace.collectAsState(initial = ResultState.Loading).value.let {
            when(it){
                is ResultState.Loading->{
                    showLoading=true
                    val token = pref?.token.toString()
                    viewModel.getDetailPlace(token,id)
                }
                is ResultState.Success->{
                    showLoading=false
                    val images = arrayListOf<String>()
                    if (!it.data.data?.image.isNullOrEmpty()){
                        it.data.data?.image?.forEach { place ->
                            images.add(place?.image.toString())
                        }
                    }

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
                                state = pagerState
                            ) { currentPage ->
                                Card(
                                    modifier = Modifier
                                        .padding(end = 7.dp)
                                        .fillMaxWidth(),
                                    elevation = CardDefaults.cardElevation(8.dp)
                                ) {
                                    AsyncImage(
                                        model= images[currentPage],
                                        contentDescription = stringResource(id = R.string.image)+" "+ (it.data.data?.name?:""),
                                        contentScale = ContentScale.FillBounds,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(230.dp)
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
                            Column(modifier= Modifier.weight(2f)) {
                                Text(
                                    modifier = Modifier
                                        .padding(top = 10.dp),
                                    text = it.data.data?.name.toString(),
                                    color = Color(0xFF000080),
                                    style = MaterialTheme.typography.titleLarge.copy(
                                        fontWeight = FontWeight.Bold,
                                        fontStyle = FontStyle.Normal
                                    ),
                                )
                            }
                            Column(modifier= Modifier.weight(0.5f)) {
                                Row(verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.End
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ticket_fill),
                                        contentDescription = stringResource(R.string.ticket_logo),
                                        modifier = Modifier
                                            .padding(top = 12.dp)
                                            .size(28.dp),
                                        tint = Color(0xFF000080)
                                    )
                                    Text(
                                        modifier = Modifier
                                            .padding(start = 6.dp, top = 10.dp, end = 7.dp),
                                        text = if (it.data.data?.price != null) context.getString(R.string.price_destination, it.data.data.price) else "-",
                                        color = Color(0xFF000080),
                                        style = TextStyle.Default.copy(
                                            fontWeight = FontWeight.Bold,
                                            fontStyle = FontStyle.Normal,
                                            fontSize = 22.sp)
                                    )
                                }
                            }
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
                                        text = stringResource(R.string.tab_about),
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
                                        text = stringResource(R.string.tab_transportation),
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
                            Tab(
                                selected = tabState.currentPage == 2,
                                text ={
                                    Text(
                                        text = stringResource(R.string.tab_reviews),
                                        style = MaterialTheme.typography.titleSmall.copy(
                                            fontWeight = FontWeight.Normal,
                                            fontStyle = FontStyle.Normal
                                        )
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
                                        text = stringResource(R.string.tab_tour_guide),
                                        style = MaterialTheme.typography.titleSmall.copy(
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
                                0 -> {
                                    AboutScreen(aboutDestination = it.data.data?.about)
                                }

                                1 -> {
                                    val token = pref?.token.toString()
                                    TransportationScreen(idDestination = it.data.data?.placeId.toString().trim().toInt(),viewModel=viewModel, navigateToDetailTransport = navigateToDetailTransport, token = token)
                                }

                                2 -> ReviewsScreen()
                                3 -> TourGuideScreen()
                            }
                        }
                    }


                }
                is ResultState.Error->{
                    showLoading=false
                    Toast.makeText(context, it.error, Toast.LENGTH_SHORT ).show()
                }
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
}