package com.dicoding.definderapps.ui.detail.transportation

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dicoding.definderapps.R
import com.dicoding.definderapps.ViewModelFactory
import com.dicoding.definderapps.ui.common.ResultState
import com.dicoding.definderapps.ui.component.detail.transportation.DetailTransportationItem
import com.dicoding.definderapps.ui.detail.DetailViewModel
import com.yogi.foodlist.ui.common.UiState
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DetailTransportationScreen(
  idDestination:Int,
  transportType:String,
  navigateBack: () -> Unit,
  viewModel:DetailViewModel = viewModel(factory= ViewModelFactory.getInstance(LocalContext.current))
) {
    var showLoading by rememberSaveable { mutableStateOf(false) }
    val pref by viewModel.getSession().observeAsState()
    Box(modifier = Modifier.fillMaxSize()){
        if (showLoading){
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
        viewModel.dataDetailtransport.collectAsState(initial = ResultState.Loading).value.let {
            when(it){
                is ResultState.Loading->{
                    showLoading=true
                    val token = pref?.token.toString()
                    viewModel.getDetailTransport(token,idDestination,transportType)
                }
                is ResultState.Success-> {
                    showLoading = false
                    LazyColumn(contentPadding = PaddingValues(10.dp)){
                        stickyHeader {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(MaterialTheme.colorScheme.background)
                            ) {
                                Box(modifier = Modifier.fillMaxWidth()) {
                                    IconButton(
                                        onClick = navigateBack,
                                        modifier = Modifier.align(Alignment.CenterStart)
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.ArrowBack,
                                            contentDescription = stringResource(R.string.back_to_detail)
                                        )
                                    }
                                    Text(
                                        text = "Detail "+ when (transportType) {
                                            "land" -> {
                                                stringResource(R.string.transport_type_1)
                                            }

                                            "air" -> {
                                                stringResource(R.string.transport_type_2)
                                            }

                                            else -> {
                                                stringResource(R.string.transport_type_3)
                                            }
                                        },
                                        style = MaterialTheme.typography.titleMedium.copy(
                                            fontWeight = FontWeight.Bold,
                                            fontStyle = FontStyle.Normal
                                        ),
                                        modifier = Modifier.align(Alignment.Center)
                                    )

                                }
                            }
                        }
                        if (!it.data.data.isNullOrEmpty()){
                            items(it.data.data, key = {it?.id.toString().trim().toInt()}){list ->
                                DetailTransportationItem(
                                    name = list?.userName.toString(),
                                    image = R.drawable.profile_default,
                                    transportationName = list?.name.toString(),
                                    transportationDesc = list?.description.toString()
                                )
                            }
                        }

                    }


                }
                is ResultState.Error->{
                    showLoading=false
                    Toast.makeText(LocalContext.current, it.error, Toast.LENGTH_SHORT).show()
                }
            }
        }

    }


}

@Preview(
    showBackground = true
)
@Composable
fun DetailTransportationScreenPreview() {
}