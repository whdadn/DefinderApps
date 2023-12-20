package com.dicoding.definderapps.ui.detail.transportation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dicoding.definderapps.R
import com.dicoding.definderapps.ui.component.detail.transportation.TransportationItem
import com.dicoding.definderapps.ui.detail.DetailViewModel


@Composable
fun TransportationScreen(
    idDestination:Int,
    transportType:List<String>,
    viewModel:DetailViewModel,
    navigateToDetailTransport:(Int,String)->Unit
) {
    var showDialog by rememberSaveable { mutableStateOf(false) }
    LazyColumn() {
        item {
            Button(
                onClick = {
                          showDialog = true
                },
                modifier = Modifier
                    .padding(top = 17.dp),
                colors = ButtonDefaults.buttonColors(Color(0xFF000080)),
                shape = RoundedCornerShape(4.dp),
                contentPadding = PaddingValues(7.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(id = R.string.add_transportation),
                )
                Text(
                    text = "Information",
                    color = Color(0xFFE6E6F2),
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Normal,
                        fontStyle = FontStyle.Normal
                    )
                )
            }
        }
        items(transportType){transportType->
            TransportationItem(
                typeTransportation = when (transportType) {
                    "land_transport"->{
                        stringResource(R.string.transport_type_1)
                    }
                    "air_transport"->{
                        stringResource(R.string.transport_type_2)
                    }
                    else->{
                        stringResource(R.string.transport_type_3)
                    }
                },
                modifier = Modifier.clickable {
                    navigateToDetailTransport(idDestination, transportType)
                }
            )
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