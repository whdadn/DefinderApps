package com.dicoding.definderapps.ui.detail.transportation

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dicoding.definderapps.R
import com.dicoding.definderapps.data.remote.response.typetransport.TransportationItem
import com.dicoding.definderapps.ui.common.ResultState
import com.dicoding.definderapps.ui.component.detail.transportation.TransportationItem
import com.dicoding.definderapps.ui.detail.DetailViewModel


@Composable
fun TransportationScreen(
    idDestination:Int,
    token:String,
    viewModel:DetailViewModel,
    navigateToDetailTransport:(Int,String)->Unit
) {
    var showDialog by rememberSaveable { mutableStateOf(false) }
    var showLoading by rememberSaveable { mutableStateOf(false) }
    Box(modifier = Modifier.fillMaxSize()){
        if (showLoading){
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
        viewModel.dataTypeTransport.collectAsState(initial = ResultState.Loading).value.let {typeTransport->
            when(typeTransport){
                is ResultState.Loading->{
                    showLoading=true
                    viewModel.getTransport(token,idDestination)
                }
                is ResultState.Success->{
                    showLoading=false
                    LazyColumn {
                        item {
                            Button(
                                onClick = {
                                    showDialog = true
                                },
                                modifier = Modifier
                                    .padding(top = 17.dp),
                                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
                                shape = RoundedCornerShape(4.dp),
                                contentPadding = PaddingValues(7.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Add,
                                    contentDescription = stringResource(id = R.string.add_transportation),
                                    tint = MaterialTheme.colorScheme.onPrimary,
                                    modifier = Modifier
                                        .padding(bottom = 4.dp)
                                )
                                Text(
                                    text = "Information",
                                    color = MaterialTheme.colorScheme.onPrimary,
                                    style = MaterialTheme.typography.bodyLarge.copy(
                                        fontWeight = FontWeight.Normal,
                                        fontStyle = FontStyle.Normal
                                    )
                                )
                            }
                        }
                        if (!typeTransport.data.data.transportation.isNullOrEmpty()) {
                            items(typeTransport.data.data.transportation, key = { it?.type.toString() }) {
                                TransportationItem(
                                    typeTransportation = when (it?.type.toString()) {
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
                                    modifier = Modifier.clickable {
                                        navigateToDetailTransport(idDestination, it?.type.toString())
                                    }
                                )
                            }
                        }
                    }

                }
                is ResultState.Error->{
                    showLoading=false
                    Toast.makeText(LocalContext.current, typeTransport.error, Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
    LaunchedEffect(showDialog) {
        if (!showDialog) {
            viewModel.getTransport(token, idDestination)
        }
    }
    if (showDialog){
        AddTransportation(closeDialog = {showDialog=false}, idDestination = idDestination, viewModel = viewModel)
    }

}

@Preview(
    showBackground = true,
)
@Composable
fun TransportationScreenPreview() {
}