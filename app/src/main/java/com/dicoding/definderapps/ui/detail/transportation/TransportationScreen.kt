package com.dicoding.definderapps.ui.detail.transportation

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dicoding.definderapps.ui.component.detail.transportation.TransportationItem

data class Transportation(
    val id: Int,
    val title: String,
    val price: Double,
)

val datas = listOf(
    Transportation(
        1,
        "Bus",
        5.0,
    ),
    Transportation(
        2,
        "Motor Cycle",
        10.0,
    ),
    Transportation(
        3,
        "Pesawat",
        100.0,
    )
)

@Composable
fun TransportationScreen() {
    LazyColumn() {
        item {
            Button(
                onClick = { },
                modifier = Modifier
                    .padding(top = 17.dp),
                colors = ButtonDefaults.buttonColors(Color(0xFF000080)),
                shape = RoundedCornerShape(4.dp),
                contentPadding = PaddingValues(7.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "add_transportation",
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

        items(datas) { list ->
            TransportationItem(title = list.title, price = list.price)
        }
    }
}

@Preview(
    showBackground = true,
)
@Composable
fun TransportationScreenPreview() {
    TransportationScreen()
}