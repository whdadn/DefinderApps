package com.dicoding.definderapps.ui.detail.transportation

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import com.dicoding.definderapps.data.local.dao.TransportData
import com.dicoding.definderapps.ui.component.detail.transportation.DetailTransportationItem
import com.dicoding.definderapps.ui.detail.DetailViewModel
import com.yogi.foodlist.ui.common.UiState

@Composable
fun DetailTransportationScreen(
  idDestination:Int,
  transportType:String,
  navigateBack: () -> Unit,
  viewModel:DetailViewModel = viewModel(factory= ViewModelFactory.getInstance(LocalContext.current))
) {
    viewModel.uiStateDetailTransportDataDestination.collectAsState(initial = UiState.Loading).value.let {
        when(it){
            is UiState.Loading->{
                viewModel.getDetailTransportData(idDestination, transportType)
            }
            is UiState.Success->{
                DetailTransportContent(data = it.data, transportType= transportType, navigateBack  = navigateBack)

            }
            is UiState.Error->{
                Toast.makeText(LocalContext.current, it.errorMessage, Toast.LENGTH_SHORT ).show()
            }
        }
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DetailTransportContent(
    data: List<TransportData>,
    transportType: String,
    navigateBack:()->Unit
){
    LazyColumn(
        contentPadding = PaddingValues(10.dp),
    ){
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
                            "land_transport" -> {
                                stringResource(R.string.transport_type_1)
                            }

                            "air_transport" -> {
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
        items(data){list ->
            DetailTransportationItem(
                name = list.name,
                image = list.image,
                transportationName = list.transportationName,
                transportationDesc = list.transportationDesc
            )
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
fun DetailTransportationScreenPreview() {
}